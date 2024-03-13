package ezenweb.controller;

import ezenweb.model.dto.ProductDto;
import ezenweb.service.MemberService;
import ezenweb.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;

    // 1. 등록 서비스/기능처리 요청
    @PostMapping("/register.do")
    @ResponseBody
    public boolean doPostRegister(ProductDto productDto){
        System.out.println("ProductController.doPostRegister");

        // 1. 작성자 처리
        Object object = request.getSession().getAttribute("loginDto");
        if (object == null){
            return false;
        }
        productDto.setMno(memberService.doGetLoginInfo((String)object).getNo());
        // productDto.setMno(1);
        return productService.doPostRegister(productDto);
    }

    // 2. 제품 출력(지도에 출력할) 요청
    @GetMapping("/list.do")
    @ResponseBody
    public List<ProductDto> doGetProductList(){
        System.out.println("ProductController.doGetProductList");
        return productService.doGetProductList();
    }

    // 3. 해당 제품의 찜하기 등록
        // 언제실행 : 로그인했고 찜하기버튼 클릭 시 , 매개변수 : pno , 리턴 : boolean(등록 성공/실패)
    @PostMapping("/plike.do")
    @ResponseBody
    public boolean doGetPlikeCreate(@RequestParam int pno){
        System.out.println("ProductController.doGetPlikeCreate");

        Object object = request.getSession().getAttribute("loginDto");
        if (object == null){
            return false;
        }
        int mno = memberService.doGetLoginInfo((String)object).getNo();

        return productService.doGetPlikeCreate(pno,mno);
    }

    // 4. 해당 제품의 찜하기 상태 출력
        // 언제실행 : 로그인했고 찜하기버튼 출력 시 , 매개변수 : pno , 리턴 : boolean(등록 있다/없다)
    @GetMapping("/plike.do")
    @ResponseBody
    public boolean doGetPlikeRead(@RequestParam int pno){
        System.out.println("ProductController.doGetPlikeRead");

        Object object = request.getSession().getAttribute("loginDto");
        if (object == null){
            return false;
        }
        int mno = memberService.doGetLoginInfo((String)object).getNo();

        return productService.doGetPlikeRead(pno,mno);
    }

    // 5. 해당 제품의 찜하기 취소/삭제
        // 언제실행 : 로그인했고 찜하기버튼 클릭 시 , 매개변수 : pno , 리턴 : boolean(취소 성공/실패)
    @DeleteMapping("/plike.do")
    @ResponseBody
    public boolean doGetPlikeDelete(@RequestParam int pno){
        System.out.println("ProductController.doGetPlikeDelete");

        Object object = request.getSession().getAttribute("loginDto");
        if (object == null){
            return false;
        }
        int mno = memberService.doGetLoginInfo((String)object).getNo();

        return productService.doGetPlikeDelete(pno,mno);
    }

    // ============= 화면 요청

    // 1. 등록 페이지/화면/뷰 요청
    @GetMapping("/register")
    public String ProductRegister(){
        return "ezen/product/register";
    }

    // 2. 제품 지도 페이지/화면/뷰 요청
    @GetMapping("/list")
    public String ProductList(){
        return "ezen/product/list";
    }
}
