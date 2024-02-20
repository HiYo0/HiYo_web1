package example.day08._2인과제2.day08model;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class Day08Dao {//class start

    // ---------- JDBC DB연동 ----------//
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public Day08Dao(){
        try {
            // 1. mysql JDBC 호출 ( 각 회사별  상이 , 라이브러리 다운로드 )
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 해당 db서버의 주소와 db연동
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day08", "root", "1234");
            System.out.println("DB됨");
        }catch (Exception e ){   System.out.println(e); }
    }
    // ---------- JDBC DB연동 END ----------//
    // 1, 저장
    public boolean create(Day08Dto dto){
        System.out.println("Day08Dao.create");
        try {
            String sql = "insert into board(bcontent,bwriter,bpassword)values(?,?,?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1,dto.getBcontent());
            ps.setString(2,dto.getBwriter());
            ps.setString(3,dto.getBpassword());
            int count = ps.executeUpdate();
            if(count==1){return true;}
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

    // 2. 전체 호출
    public List<Day08Dto> read(){
        System.out.println("Day08Dao.read");
        List<Day08Dto> day08Dtos = new ArrayList<>();
        try {
            String sql = "select * from board";
            ps = conn.prepareStatement(sql);
            ps.executeQuery();
            while (rs.next()){
                Day08Dto day08Dto = new Day08Dto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                day08Dtos.add(day08Dto);
            }
            return day08Dtos;

        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return null;
    }

    // 3. 수정
    public boolean update(Day08Dto dto){
        System.out.println("Day08Dao.update");
        try {
            String sql = "update board set bcontent=?,bwriter=?,bpassword=? where bno=?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1,dto.getBcontent());
            ps.setString(2,dto.getBwriter());
            ps.setString(3,dto.getBpassword());
            ps.setInt(4,dto.getBno());
            int count = ps.executeUpdate();
            if(count==1){return true;}
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }


    // 4. 삭제
    public boolean delete(int bno){
        System.out.println("Day08Dao.delete");
        try {
            String sql = "delete from board where bno="+bno+";";
            ps = conn.prepareStatement(sql);
            int count = ps.executeUpdate();
            if(count==1){return true;}
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }


}// class end
