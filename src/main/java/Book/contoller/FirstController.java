package Book.contoller;

import org.springframework.stereotype.Controller;   // @Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 이 클래스가 컨트롤러임을 선언
public class FirstController {//class start

    @GetMapping("/hi")// http://localhost:80/hi
    public String niceToMeet2You(Model model){// 인수에 model 매게변수 선언
        // return "머스테치파일명";

        model.addAttribute("username","유재석");
        return "greetings";
        // 서버가 알아서 templates 폴더에서 파일을 찾아 브라우저에게 전송
    }//method end


    @GetMapping("/bye")// http://localhost:80/bye
    public String seeYouNext(Model model){
        // 머스터치에 전달할 변수 등록
        model.addAttribute("nickname","HiYo0");
        return "goodbey";
    }

}//class end
