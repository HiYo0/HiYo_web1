package hiyoweb.controller;

import hiyoweb.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/auth")
public class AuthController {//class start

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EmailService emailService;

    //1. email 인증 요청
    @GetMapping("/email/req")
    public boolean getEmailReq(String email){
        System.out.println("AuthController.getEmailReq");

        // 1. 난수 이용한 6자리 인증코드 발급
            // 1. 난수객체 생성
        Random random =new Random();
            // 2. 6번 반복
        String ecode = "";
        for (int i = 1; i <= 6; i++) {
            // 3. 난수생성해서 변수에 누적 문자로 연결하기
                // 정수 난수 생성
            ecode += random.nextInt(10);    // 10미만 : 0~9 // .nextInt( 미만 ) + 시작번호

        }
        System.out.println("ecode = " + ecode);

        // 2. HTTP세션에 특정시간만큼 인증코드 보관
            // 1. 세션 속성추가 해서 발급된 인증코드 대입
        request.getSession().setAttribute("ecode",ecode);
            // 2. 세션의 생명주기 추가하기 ( 초단위 )
        request.getSession().setMaxInactiveInterval(120); // 초기준 // 10초동안 세션 유지하고 10초후 삭제

        // 3. 발급된 인증코드를 인증할 이메일로 전송
        emailService.send(email,"제목(전승호 인증코드)","인증코드 : "+ecode);

        return true;
    }

    // 2. Email 인중확인
    @GetMapping("/email/check")
    public boolean getEmailCheck(@RequestParam String ecodinput){
        System.out.println("AuthController.getEmailCheck");
        System.out.println("ecodinput = " + ecodinput);
        
        // 1. HTTP세션에 보관했던 인증코드를 꺼내서
            // 1. 세션 속성 호출
        Object object = request.getSession().getAttribute("ecode");
            // 2. 만약에 세션속성이 존재하면
        if(object != null){
            String ecode = (String)object; // 강제 타입변환
            // 3. 발급된 인증코드와 입력받은 인증코드와 같으면
            if(ecode.equals(ecodinput)){
                return true;
            }
        }
        return false;
    }//method getEmailCheck End

}//class end
