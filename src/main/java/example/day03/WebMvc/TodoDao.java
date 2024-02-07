package example.day03.WebMvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
@Slf4j
public class TodoDao {
    // 1. 필드
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    // 2. 생성자 : db연동 코드

    public TodoDao() {
        try {
            // 1. jdbc 라이브러리 호출
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 연동
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb",
                    "root","1234");
            System.out.println("DB success");
        }catch (Exception e){
            log.error(e.toString());
        }
    }//method end

    //3. 메소드
        // 2. 할일등록 함수
    public boolean doPost(TodoDto todoDto){
        try {
            // 1. sql작성
            String sql = "insert into todo(content,deadline) values(?,?);";
            // 2. sql기재
            ps = conn.prepareStatement(sql);
            // 3. sql 매개변수 정의
            ps.setString(1,todoDto.getContent());
            ps.setString(2,todoDto.getDeadline());
            // 4. sql 실행
            int count = ps.executeUpdate();
            // 5. sql 실행결과
            if(count==1){return true;}
            // 6. 함수리턴

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }// method end
        // 3. 할일 출력함수
    public ArrayList<TodoDto> doGet(){
        // 0. 반환할 todoList 객체
        ArrayList<TodoDto> list = new ArrayList<>();

        try {
            // 1. sql작성
            String sql = "select * from todo;";
            // 2. sql기재
            ps = conn.prepareStatement(sql);
            // 3. sql 매개변수 정의;
            // 4. sql 실행
            rs = ps.executeQuery();
            // 5. sql 실행결과

            while (rs.next()){// next() 레코드 이동.
                // 레코드 1개당 dto 1개
                TodoDto todoDto = new TodoDto(
                        rs.getInt("id"),
                        rs.getString("content"),
                        rs.getString("deadline"),
                        rs.getBoolean("state")
                );
                list.add(todoDto);
            }//while end
            // 6. 함수리턴
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return list;
    }// method end

    // 4. 할일 상태수정   함수
    public boolean doPut(TodoDto todoDto) {
        try {
            // 1. sql작성
            String sql = "update todo set state = ? where id = ? ;";
            // 2. sql기재
            ps = conn.prepareStatement(sql);
            // 3. sql 매개변수 정의;
            ps.setBoolean(1, todoDto.isState());
            ps.setInt(2,todoDto.getId());
            // 4. sql 실행
            int count = ps.executeUpdate(); // executeUpdate() sql 실행후 반영된 레코드수 반환
            // 5. sql 실행결과
            if(count == 1){return true;}
            // 6. 함수리턴

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }// method end

    // 5. 할일 삭제 함수
    public boolean doDelete(int id){
        try {
            // 1. sql작성
            String sql = "delete from todo where id= ? ;";
            // 2. sql기재
            ps = conn.prepareStatement(sql);
            // 3. sql 매개변수 정의;
            ps.setInt(1,id);
            // 4. sql 실행
            int count = ps.executeUpdate();
            // 5. sql 실행결과
            if(count == 1){return true;}
            // 6. 함수리턴

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
