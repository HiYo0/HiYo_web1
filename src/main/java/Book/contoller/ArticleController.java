package Book.contoller;

import Book.dao.ArticleDao;
import Book.dto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// ArticleController 만든이유 mustache 파일 실행시키기위해( 랜더링 시키기위해서 )

@Controller //
@Slf4j // 자바에서 간단한 로그 처리
// 1. 스프링 컨테이너(메모리 저장소)에 빈(객체) 등록
// 2. 스프링이 해당 클래스를 사용할수 있다.
// 3. 모든 클라이언트 요청은 컨트롤러로 들어온다.
public class ArticleController {//class start

    @Autowired // 스프링 컨테이너에 등록된 빈 주입한다.
    ArticleDao articleDao;


    // 1. 글쓰기 페이지 반환
    @GetMapping("/articles/new") // HTTP 요청 경로 : GET방식 // http://localhost:80/articles/new
    public String newArticleForm(){
        return "articles/new";   // .확장자 빼고 resources/template

    }

    // 2. 글쓰기 처리
        // 1. <form action="/articles/create" method= "post">
        // 2. 입력태그 속성의 name 과 DTO 필드의 필드명 일치하면 자동 연결/대입 된다.
        // 3. public 생성자 필요
    @PostMapping("/articles/create")    // HTTP 요청 경로 : Post방식 // http://localhost:80/articles/create
    public boolean createArticle(ArticleForm form){
        // soutm : 메소드명 출력
        System.out.println("ArticleController.createArticle");
        // soutp : 메소드 매개변수 출력
        System.out.println("form = " + form);

        //개발용( 디버그 )
        log.debug(form.toString());

        // ( 경고 ) 로그
        log.warn(form.toString());

        // ( 에러 ) 로그
        log.error(form.toString());
        
        // ( 정보 ) 로그
        log.info(form.toString() ); // 자동완성 : 메뉴 -> 파일 -> 설정 -> 플러그인 -> 마켓플레이스 -> Lombok 설치

        // DAO 에게 요청하고 응답받기.
        boolean result = articleDao.creareArticle(form);
        return result;
    }



}//class end
/*
    @ = 어노테이션
        1. 표준어노테이션 : 자바에서 기본적으로 제공
            @Override : 메소드 재정의
            등등
        2. 메타 어노테이션 : p.64
            - 소트코드에 추가해서 사용하는 메타 데이터
            - 메타 데이터 : 구조화된 데이터
            - 컴파일 또는 실행 했을때 코드를 어떻게 처리 해야할지
            @SpringBootApplication
                - 1. 내장 서버(톰캣) 지원
                - 2. 스프링 MVC 내장
                    view : resource
                    controller : @Controller , @RestController
                    model(Dao) : @Service @Repository
                        entity(DB table) : @Entity
                        그 외 별도  : @Component
                        설정 클래스 : @Configuration
                - 3. 컴포넌트(module) 스캔* : MVC 클래스 스캔
                    동일 패키지내 혹은 하위 페키지내

            @Controller
            @GetMapping

 */
