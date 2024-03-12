package ezenweb.service;

import ezenweb.model.dao.ProductDao;
import ezenweb.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private FileService fileService;

    // 1. 등록 서비스/기능처리 요청
    public boolean doPostRegister(ProductDto productDto){
        System.out.println("ProductService.doPostRegister");

        // 1. 첨부파일 업로드 처리
        List<String> list = new ArrayList<>();
        productDto.getUploadFiles().forEach((uploadFile)->{
            String fileName = fileService.fileUpload(uploadFile);
            if (fileName != null){
                list.add(fileName);
            }
        });
        productDto.setPimg(list);

        return productDao.doPostRegister(productDto);
    }

    // 2. 제품 출력(지도에 출력할) 요청
    public List<ProductDto> doGetProductList(){
        System.out.println("ProductService.doGetProductList");
        return productDao.doGetProductList();
    }
}
