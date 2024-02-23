package example.day11._2Controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.http.HttpResponse;

@Controller
public class RestController1 {//class start

    // 1. Get
    @RequestMapping(value = "/day11/black",method = RequestMethod.GET)
    public void getBlack(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        System.out.println("RestController1.getBlack");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        resp.setContentType("text/html");
        resp.getWriter().println("클라이언트에게 : 안녕");
    }
    // 2. Post
    @RequestMapping(value = "/day11/black",method = RequestMethod.POST)
    public void postBlack(HttpServletRequest req , HttpServletResponse resp){
        System.out.println("RestController1.postBlack");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);

        // 응답
        resp.setContentType("text/html");
        try {resp.getWriter().println("클라이언트에게 : 안녕");
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    // 3. Put
    @RequestMapping(value = "/day11/black",method = RequestMethod.PUT)
    public void putBlack(HttpServletRequest req , HttpServletResponse resp){
        System.out.println("RestController1.putBlack");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);

        // 응답
        resp.setContentType("text/html");
        try {resp.getWriter().println("클라이언트에게 : 안녕");
        } catch (IOException e) {throw new RuntimeException(e);}

    }

    // 4. delete
    @RequestMapping(value = "/day11/black",method = RequestMethod.DELETE)
    public void deleteBlack(HttpServletRequest req , HttpServletResponse resp){
        System.out.println("RestController1.deleteBlack");
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);

        // 응답
        resp.setContentType("text/html");
        try {resp.getWriter().println("클라이언트에게 : 안녕");
        } catch (IOException e) {throw new RuntimeException(e);}
    }

}//class end
