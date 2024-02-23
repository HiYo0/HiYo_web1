package example.day11._1Survlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
// 서블릿 선언 하는 방법
// 1. 해당 클래스에 HttpServlet 상속받는다.
// 2. 해당 클래스에 @WebServlet("HTTP식별주소") 어노테이션 주입해서 web.xml 에 등록한다.
// 3. HttpServlet 가 제공하는 메소드를 오버라이딩 : init() , service() , doXXX , destroy()

    /* 서블릿 실행 구동 순서
        1. 클라이언트(브라우저) HTTP 요청(Request) 이 (AWS(톰캣서버) )들어온다
        2. 서블릿컨테이너에 요청받은 서블릿이 있는지 없는지 판단
        3. 없으면 init() 메소드 실행한 서블릿 생성
        4. 있으면 또는 생성했으면 Thread(작업스레드) 할당
        5. service() 실행 하고 HTPP 요청 method에 따른 메소드로 이동
        6. doXXX 메소드 실행될때 요청(HttpServletRequest) 객체 생성
            - HTTP 관련된 정보를 요청할수 있는 기능 가지고 있다.
        7. doXXX 메소드 종료될때 응답(HttpServletResponse) 객체 생성
            - HTTP 관련된 정보를 응답할수 있는 기능 가지고 있다.
        -------- 다음 요청이 올때까지.
        1 -> 2 -> 4 -> 5 -> 6
        -------- 서버가 종료되면 destroy() 실행되면서 안전하게 서블릿 제거
    */

@WebServlet("/servlet") // http://localhost/servlet
public class TestServlet extends HttpServlet {//class start


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doGet");
        // 요청 객체 : HttpServletRequest

        String id = req.getParameter("id");
        System.out.println("id = " + id);

        int type = Integer.parseInt(req.getParameter("type"));
        System.out.println("type = " + type);

        // 응답 객체 : HttpServletResponse
        // resp.setContentType("text/html"); // 데이터의 타입( 받는 입장에서의 데이터를 사용하는 방법 )
        resp.setContentType("application/json"); // 데이터의 타입( 받는 입장에서의 데이터를 사용하는 방법 )
        resp.getWriter().print("{ \"msg\" : \"서블릿이 보내온 메세지\" , \"type\" : \"1\" }");
        // 자바 데이터를 JSON 데이터 형식 변환
            // 1. 직접한다  ( 실무에서는 불가능 )
            // 2. 라이브러리 사용함
            // 3. 스프링MVC @ResponseBody


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doPost");
        resp.setContentType("text/html");
        resp.getWriter().println("글");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doPut");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TestServlet.doDelete");
    }
}//class end
