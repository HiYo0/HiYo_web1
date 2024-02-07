package Book.dao;

import Book.dto.ArticleForm;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component // 스프링 컨테이너에 해당 클래스를 빈(객체) 등록
public class ArticleDao {
    // = DB연동============================================================//
        // 1. DB연동 필요한 인터페이스( 구현객체 -> 각회사(mysql com.mysql.cj.jdbc 패키지내 Driver클래스 ) ) 필드 선언
    private Connection conn;      // DB연동객체
    private PreparedStatement ps; // 작성된 SQL 가지고 있고 , 실행 담당. //
    private ResultSet rs;         //테이블에서 값을 가져옴
        // 2. 생성자
    public ArticleDao() {
        // - 객체 생성시 DB연동.
        try {
            // 1. mySQL 회사의 JDBC관련된(Driver) 객체를 JVM에 로딩한다. 불러오기.
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동된 결과의 (구현체)객체를 Connection 인터페이스에 대입한다.
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb",
                    "root",
                    "1234"
            );
            System.out.println("DB연동 성공");
        } catch (Exception e) {
            System.out.println("DB서버 연동오류남" + e);
        }
    }
    // = DB연동end =============================================================== //

    public boolean creareArticle(ArticleForm form){
        System.out.println("ArticleDao.createArticle");
        try {
            String sql ="insert into article (title , content )values(?,?);";
            ps = conn.prepareStatement( sql );
            ps.setString(1, form.getTitle());
            ps.setString(2, form.getContent());
            // 4.
            int count = ps.executeUpdate();
            // 5.
            if(count==1){return true;}
        }catch (Exception e){
            System.out.println(e);
        }

        return false;
    }

}// class end
