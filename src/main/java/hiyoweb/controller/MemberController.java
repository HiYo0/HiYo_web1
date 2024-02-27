package hiyoweb.controller;

import hiyoweb.model.dao.MemberDao;
import hiyoweb.model.dto.LoginDto;
import hiyoweb.model.dto.MemberDto;
import hiyoweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
public class MemberController {//class start


    @Autowired
    private MemberDao dao;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;

    // 1단계. view <-----> controller 사이의 HTTP 통신 방식 설계
    // 2단계. Controller mapping 체크 ( API Tester )
    // 3단계. Controller request 변수
        // ----------------- Dto , Service ---------------- //
    // 4단계. 응답 : 1.뷰 반환 : text/html;  vs  2.데이터 : @ResponseBody : Application/JSON


    // ================ 1. 회원가입 처리 요청 ================ //
    @PostMapping("/hiyoweb/signup") // http://localhost:80/hiyoweb/signup
    @ResponseBody // 응답방식 application/JSON 방식
    public boolean signup(MemberDto form){
        System.out.println("회원가입 실행됨"); // 실행확인

        return memberService.Signup(form); // Dao 처리
    }//function end


    // ================ 2. 로그인 처리 요청 / 세션 저장 ================ //
    @PostMapping("/hiyoweb/login") // http://localhost:80/hiyoweb/login
    @ResponseBody
    public boolean login(LoginDto loginDto){
        System.out.println("로그인 실행됨");
        System.out.println("loginDto = " + loginDto);

        boolean result = dao.login(loginDto); // Dao 처리
        System.out.println("로그인처리 여부 = " + result);
        // * 로그인 성공시

        // 세션 저장소 : 톰켓서버에 *브라우저 마다의 메모리 할당
        // 세션 객체 타입 : Object ( 여러가지의 타입들을 저장할려고 )
        // 1. Http요청 객체 호출.     HttpServletRequest
        // 2. Http세션 객체 호출.     .getSession()
        // 3. Http세션 데이터 저장.    .setAttribute( "세션명" , 데이터 );   -- 자동형변환 ( 자식 --> 부모 )
        // -  Http세션 데이터 호출.    .getAttribute( "세션속성명" );        -- 강제형변환 ( 부모 --> 자식 ) / 캐스팅
        // -  Http세션 데이터 초기화.  .invalidate()
        MemberDto loginNo = null;
        if(result){loginNo = dao.memberNo(loginDto);}
        if(result){request.getSession().setAttribute("loginDto",loginNo.getId());}

        //
        return result;
    }
    // ================ 2-2. 로그인 여부 확인 요청 / 세션 호출 ================ //
    @GetMapping("/hiyoweb/login/check")
    @ResponseBody
    public String doGetLoginCheck(){
        // *로그인 여부 확인 = 세션이 있다 없다 확인
            // 1 -> http 요청 객체 호출 , 2 -> http세션 객체 호출 -> 3. http 세션 데이터 호출

        // null 형변환이 불가능하기 때문에 유효성 검사
        String loginDto = null;
        Object sessionObj = request.getSession().getAttribute("loginDto");
        if(sessionObj != null){loginDto = (String) sessionObj; }
        // 만약에 로그인을했으면 (세션에 데이터가 있으면) 강제 형변환을 통해 데이터 호출 아니면 0
        System.out.println("loginDto = " + loginDto);

        return loginDto;
    }// function end

    // ================ 2-3. 로그아웃 ================ //
    @GetMapping("/hiyoweb/logout")
    @ResponseBody // 응답받을 대상이 JS ajax
    public boolean doGetLoginOut(){

        // 1. 로그인 관련 세션 초기화.
            // 방법1. 모든 세션 초기화( 모든 세션의 속성이 초기화 -> 로그인세션 외 다른 세션도 고려 )
            request.getSession().invalidate(); // 현제 요청 보낸 브라우저의 모든 세션 초기화
            // 방법2. 특정 세션속성 초기화 => 동일한 세션속성명에 null 대입한다
            // request.getSession().setAttribute("loginDto",null);

        return true;
        // 로그아웃 성공시 => 메인페이지 또는 로그인페이지
    }

    // ================ 3. 회원정보 요청(호그인된 회원 요청 , 페스워드 제외) ================ //
    @GetMapping("/hiyoweb/login/info")
    @ResponseBody
    public MemberDto doGetLoginInfo(LoginDto loginDto){

        System.out.println("loginDto2 = " + loginDto);
        MemberDto result = memberService.doGetLoginInfo(loginDto);
        System.out.println("result2 = " + result);

        return result;
    }

    // ================ 4. 회원가입 페이지 요청 ================ //
    @GetMapping("/hiyoweb/signup")
    public String viewSignup(){
        System.out.println("페이지요청 실행됨");
        return "/hiyoweb/signup";
    }

    // ================ 5. 로그인 페이지 요청 ================ //
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
