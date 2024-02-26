package hiyoweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ================ 스프링 부트 실행 ============= //
@SpringBootApplication
public class AppStart {//class start

    public static void main(String[] args) {//main start
        SpringApplication.run((AppStart.class));
    }// main end
}//class end

// 홈페이지 favicon( 탭 로고 ) 
    // - ico 확장자 이미지파일
    // - resources -> static -> favicon.ico 로 저장
