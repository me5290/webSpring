package ezenweb.controller;

import ezenweb.model.dto.ProductDto;
import ezenweb.service.MemberService;
import ezenweb.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
//        Object object = request.getSession().getAttribute("loginDto");
//        if (object == null){
//            return false;
//        }
//        productDto.setMno(memberService.doGetLoginInfo((String)object).getNo());
        productDto.setMno(1);
        return productService.doPostRegister(productDto);
    }

    // 2. 제품 출력(지도에 출력할) 요청
    @GetMapping("/list.do")
    @ResponseBody
    public List<ProductDto> doGetProductList(){
        System.out.println("ProductController.doGetProductList");
        return productService.doGetProductList();
    }

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
