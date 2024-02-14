package Book.dao;

import Book.dto.ArticleForm;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
    }//method end

    // ------------ ------------ ------------ //
    // 2. 개별글 조회 : 매개변수 :조회할 게시물번호(id) | 반환 : 조회한게시물정보 1개(DTO)
    public ArticleForm show( Long id ){
        try {
            String sql = "select * from article where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            if(rs.next()){ // 1개 게시물을 조회 할 예정이라서 next() 한번처리.
                ArticleForm form =new ArticleForm(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                return form;
            }


        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }//method end

    // ------------ ------------ ------------ //
    // 3. 개별 글 조회 : 매개변수 : X , 리턴타입 : ArrayList
    public List<ArticleForm> index(){
        List<ArticleForm> list = new ArrayList<>();
        try {
            String sql = "select * from article;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                // 1. 객체 만들기
                ArticleForm form =new ArticleForm(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                // 2. 객체를 리스트에 넣기
                list.add(form);
            }//while end
            return list;
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return list;
    }

}// class end
