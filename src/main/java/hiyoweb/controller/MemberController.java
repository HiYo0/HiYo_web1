package hiyoweb.controller;

import hiyoweb.model.dto.LoginDto;
import hiyoweb.model.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {//class start



    // 1단계. view <-----> controller 사이의 HTTP 통신 방식 설계
    // 2단계. Controller mapping 체크 ( API Tester )
    // 3단계. Controller request 변수
        // ----------------- Dto , Service ---------------- //
    // 4단계. 응답 : 1.뷰 반환 : text/html;  vs  2.데이터 : @ResponseBody : Application/JSON


    // ================ 1. 회원가입 처리 요청 ================ //
    @PostMapping("/member/signup") // http://localhost:80/member/signup
    @ResponseBody // 응답방식 application/JSON 방식
    public boolean signup(MemberDto memberDto){
        System.out.println("회원가입 실행됨"); // 실행확인
        System.out.println("memberDto = " + memberDto);// 매개변수 확인
        // -
        boolean result = true; // Dao 처리

        return result;
    }

    // ================ 2. 로그인 처리 요청 ================ //
    @PostMapping("/member/login") // http://localhost:80/member/login
    @ResponseBody
    public boolean login(LoginDto loginDto){
        System.out.println("로그인 실행됨");
        System.out.println("loginDto = " + loginDto);

        boolean result = true; // Dao 처리

        return result;
    }

    // ================ 3. 회원가입 페이지 요청 ================ //
    @GetMapping("/member/signup")
    public String viewSignup(){
        System.out.println("페이지요청 실행됨");
        return "/hiyoweb/signup";
    }

    // ================ 4. 로그인 페이지 요청 ================ //
    @GetMapping("/member/login")
    public String doPostLogin(MemberDto memberDto){
        System.out.println("로그인페이지 요청");
        return "/hiyoweb/login";

    }

}//class end
