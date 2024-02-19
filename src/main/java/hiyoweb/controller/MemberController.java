package hiyoweb.controller;

import hiyoweb.model.dao.MemberDao;
import hiyoweb.model.dto.LoginDto;
import hiyoweb.model.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {//class start


    @Autowired
    MemberDao dao;

    // 1단계. view <-----> controller 사이의 HTTP 통신 방식 설계
    // 2단계. Controller mapping 체크 ( API Tester )
    // 3단계. Controller request 변수
        // ----------------- Dto , Service ---------------- //
    // 4단계. 응답 : 1.뷰 반환 : text/html;  vs  2.데이터 : @ResponseBody : Application/JSON


    // ================ 1. 회원가입 처리 요청 ================ //
    @PostMapping("/hiyoweb/signup") // http://localhost:80/hiyoweb/signup
    @ResponseBody // 응답방식 application/JSON 방식
    public boolean signup(MemberDto memberDto){
        System.out.println("회원가입 실행됨"); // 실행확인
        System.out.println("memberDto = " + memberDto);// 매개변수 확인
        // -
        boolean result = true; // Dao 처리

        return result;
    }

    // ================ 2. 로그인 처리 요청 ================ //
    @PostMapping("/hiyoweb/login") // http://localhost:80/hiyoweb/login
    @ResponseBody
    public boolean login(LoginDto loginDto){
        System.out.println("로그인 실행됨");
        System.out.println("loginDto = " + loginDto);

        boolean result = true; // Dao 처리

        return result;
    }

    // ================ 3. 회원가입 페이지 요청 ================ //
    @GetMapping("/hiyoweb/signup")
    public String viewSignup(){
        System.out.println("페이지요청 실행됨");
        return "/hiyoweb/signup";
    }

    // ================ 4. 로그인 페이지 요청 ================ //
    @GetMapping("/hiyoweb/login")
    public String doPostLogin(MemberDto memberDto){
        System.out.println("로그인페이지 요청");
        return "/hiyoweb/login";

    }

    // 회원수정======================================================= //
        // 마이페이지창 띄우기
    @GetMapping("/hiyoweb/{no}/mypage")
    public String mypage(@PathVariable int no, Model model){

        MemberDto mdto = dao.uesr(no);

        model.addAttribute("mdto",mdto);

        System.out.println("mdto = " + mdto);
        return "/hiyoweb/mypage";
    }

        // 수정버튼눌럿을때 처리하기
    @PostMapping("/hiyoweb/mypage")
    public String mypagefix(MemberDto form){
        System.out.println("form = " + form);

        MemberDto dto = dao.mypagefix(form);

        return "redirect:/hiyoweb/"+form.getNo()+"/mypage";
    }

        // 삭제하기
    @GetMapping("/hiyoweb/{no}/delete")
    public String memberdelete(@PathVariable int no){
        System.out.println("삭제 실행됨");

        boolean result = dao.memberdelete(no);

        return "redirect:/hiyoweb/signup";// 이동될 페이지 넣어야됨
    }

    // 회원수정end ====================================================== //

}//class end
