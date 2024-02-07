package example.day03.WebMvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// ---------------스프링부트 실행에 관련 기능 주입 -----------------//
// 1. 내장서버(톰켓)
// ********* 2. 동등한 패키지 혹은 하위 패키지내
// @Controller
public class AppStart {//class start

    public static void main(String[] args) {
        // 1.
        SpringApplication.run(AppStart.class);

    }
}
