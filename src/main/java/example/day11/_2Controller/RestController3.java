package example.day11._2Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/day11")
public class RestController3 {//class start

    // 1. Get
    @GetMapping(value = "/red")
    public String getRed(HttpServletRequest req) {
        System.out.println("RestController1.getRed");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return "클라이언트에게 : 안녕3";
    }
    // 2. Post
    @PostMapping(value = "/red")
    public Map<String,String> postRed(HttpServletRequest req , HttpServletResponse resp){
        System.out.println("RestController1.postRed");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);

        // 응답
        /*
        String[] strArray = new String[2];
        strArray[0] = "안녕";
        strArray[1]="클라이언트";
        */

//        List<String> strArray = new ArrayList<>();
//        strArray.add("안녕"); strArray.add("클라이언트");

        Map<String,String> strArray = new HashMap<>();
        strArray.put("안녕","클라이언트");

        return strArray;
    }

    // 3. Put
    @PutMapping("/red")
    public int putRed(HttpServletRequest req , HttpServletResponse resp){
        System.out.println("RestController1.putRed");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);

        // 응답
        return 10;

    }

    // 4. delete
    @DeleteMapping("/red")
    public boolean deleteRed(HttpServletRequest req , HttpServletResponse resp){
        System.out.println("RestController1.deleteRed");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);

        // 응답
        return true;
    }
}//class end
