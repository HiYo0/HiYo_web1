package hiyoweb.controller;

import hiyoweb.model.dto.MemberDto;
import hiyoweb.model.dto.ProductDto;
import hiyoweb.service.MemberService;
import hiyoweb.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    // 3. 해당 제품의 찜하기 등록
        // 언제실행 : 로그인한 유저가 찜하기 버튼 클릭했을때
        // 매개변수 : pno
        // 리턴    : boolean ( 등록 성공/실패 )
    @PostMapping("/plike.do")
    @ResponseBody
    public boolean getPlikeWrite( @RequestParam int pno){
        System.out.println("ProductController.getPlikeWrite");
        System.out.println("pno = " + pno);

        // 1. 작성자 처리
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null){return false;}
        // 1-1. 형변환
        String  mid = (String) object;

        int mno = memberService.doGetLoginInfo(mid).getNo();


        return productService.getPlikeWrite(pno,mno);
    }

    // 4. 해당 제품의 찜하기 상태 출력
        // 언제실행 : 로그인한 유저가 찜하기 버튼 출력시
        // 매개변수 : pno
        // 리턴    : boolean ( 있다 / 없다 )
    @GetMapping("/plike.do")
    @ResponseBody
    public boolean getPlikeView(@RequestParam int pno){

        // 1. 작성자 처리
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null){return false;}
        // 1-1. 형변환
        String  mid = (String) object;

        int mno = memberService.doGetLoginInfo(mid).getNo();
        return productService.getPlikeView(pno,mno);
    }

    // 5. 해당 제품의 찜하기 취소 / 삭제
        // 언제실행 : 로그인한 유저가 찜하기 버튼 클릭했을때
        // 매개변수 : pno
        // 리턴    : boolean  ( 취소 성공/실패 )
    @DeleteMapping("/plike.do")
    @ResponseBody
    public boolean getPlikeDelete(@RequestParam int pno){

        // 1. 작성자 처리
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null){return false;}
        // 1-1. 형변환
        String  mid = (String) object;

        int mno = memberService.doGetLoginInfo(mid).getNo();
        return productService.getPlikeDelete(pno,mno);
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
