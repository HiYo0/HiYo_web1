package example.day10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication // 아파치 톰캣
@ServletComponentScan // 서블릿 찾는다.
public class SpringBootStart {//class start

    public static void main(String[] args) {//main start
        SpringApplication.run(SpringBootStart.class);
    }//main end
}//class end
