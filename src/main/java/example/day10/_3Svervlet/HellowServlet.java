package example.day10._3Svervlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 자바 회사 에서 웹개발 위한 HTTP 통신 클래스 : HttpServlet
    // 1. 해당 클래스에 HttpServlet 상속받는다.
    // 2. 해당 클래스에 @WebServlet 어노테이션 주입한다.
    // 3. HttpServlet 가 제공하는 메소드를 오버라이딩 : init() , service() , doXXX() , destroy()
/*
    서블릿 실행 구동 순서
    1. 클라이언트(브라우저) HTTP 요청이 ( AWS(톰켓서버) )들어온다
    2. 서블릿컨테이너에 요청받은 서블릿이 있는지 없는지 판단.
    3. 없으면 init() 메소드 실행한 서블릿 생성
    4. 있으면 또는 생성했으면 Thread(작업스레드) 할당.
    5. service() 실행 하고 HTTP 요청 method에 따른 메소드로 이동
    6. doXXX 메소드 실행될때 요청 (HttpServletRequest) 객체 생성
        - HTTP 관련된 정보를 응답할수 있는 기능 가지고 있다.
    --------- 다음 요청이 올때까지.
    1 -> 2 -> 4 -> 5 -> 6
    --------- 서버가 종료되면 destroy() 실행되면서 안전하게 서블릿 제거
*/

@WebServlet("/team4")
public class HellowServlet extends HttpServlet {//class start

    // HttpServlet 클래스로부터 상속받으면 다양한 Http 관련 메소드 사용
    @Override // 1. [ 최초 요청 1번 실행 ]해당 서블릿 객체가(1개)가 생성 되었을때 실행되는 메소드
    public void init(ServletConfig config) throws ServletException {
        System.out.println("HellowServlet.init");
        super.init();
    }

    @Override // 2. [ 요청마다 실행 ]해당 서블릿 으로부터 HTTP 서비스 실행 되었을때 실행되는 메소드
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HellowServlet.service");
        super.service(req, resp);
    }

    @Override // 3. [ HTTP method 따라 실행 ]서비스 요청중에 HTTP method 방식이 get이면 실행되는 메소드
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HellowServlet.doGet");
        resp.setContentType("text/html");
        resp.getWriter().println("get 메소드실행");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HellowServlet.doPost");
        resp.setContentType("text/html");
        resp.getWriter().println("Post 메소드실행됨");
    }

    @Override // 4. [ 서버가 종료될때 1번 실행 ] 해당 서블릿 객체가 삭제 되었을때 실행되는 메소드
    public void destroy() { // 4. 해당 서블릿 객체가 삭제 되었을때 실행되는 메소드
        System.out.println("HellowServlet.destroy");
        super.destroy();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HellowServlet.doPut");
        resp.setContentType("text/html");
        resp.getWriter().println("Put 메소드 실행");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HellowServlet.doDelete");
        resp.setContentType("text/html");
        resp.getWriter().println("delete 메소드 실행");
    }
}//class end
