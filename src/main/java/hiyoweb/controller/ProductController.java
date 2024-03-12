package hiyoweb.controller;

import hiyoweb.model.dto.ProductDto;
import hiyoweb.service.MemberService;
import hiyoweb.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
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
    @Autowired private ProductService productService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;


// ==================================================== 서비스 요청
    // 1. 등록 서비스요청
    @PostMapping("/register.do")
    @ResponseBody
    public boolean postProductRegister(ProductDto productDto){
        System.out.println("ProductController.postProductRegister");

        // 1. 작성자 처리
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null){return false;}
            // 1-1. 형변환
        String  mid = (String) object;

        productDto.setMno(memberService.doGetLoginInfo(mid).getNo());

        return productService.postProductRegister(productDto);
    }

    // 2. 제품출력( 지도에 출력할 내용 )요청
    @GetMapping("/list.do")
    @ResponseBody
    public List<ProductDto> getProductList(){
        System.out.println("ProductController.getProductList");

        return productService.getProductList();
    }

// ==================================================== 화면요청
    // 1. 등록 페이지/화면/뷰 요청
    @GetMapping("register")
    public String productRegister (){
        return "hiyoweb/product/register";
    }
    // 2. 지도 페이지/화면/뷰 요청
    @GetMapping("list")
    public String productList (){
        return "hiyoweb/product/list";
    }

}
