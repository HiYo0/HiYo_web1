package hiyoweb.model.dao;

import hiyoweb.model.dto.MemberDto;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class MemberDao {//class start
    // ---------- JDBC DB연동 ----------//
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public MemberDao(){
        try {
            // 1. mysql JDBC 호출 ( 각 회사별  상이 , 라이브러리 다운로드 )
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 해당 db서버의 주소와 db연동
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiyowep", "root", "1234");
            System.out.println("DB됨");
        }catch (Exception e ){   System.out.println(e); }
    }


    // 식별번호 로 유저정보 가져오기
    public MemberDto uesr(int no){
        try {
            String sql = "select * from member where no = " + no + ";";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                MemberDto memberDto = new MemberDto(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );
                return memberDto;
            }
        }catch (Exception e){System.out.println(e);}
        return null;
    }

    // 수정된 정보로 저장하기
    public MemberDto mypagefix(MemberDto dto){
        try {
            String sql = "update member set pw = ? , name = ? , email = ? , phone = ? , img = ? where no=?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1,dto.getPw());
            ps.setString(2,dto.getName());
            ps.setString(3,dto.getEmail());
            ps.setString(4,dto.getPhone());
            ps.setString(5,dto.getImg());
            ps.setInt(6,dto.getNo());
            int count = ps.executeUpdate();
            if(count ==1){
                return dto;
            }
        }catch (Exception e){System.out.println(e);}
        return null;
    }

    // 회원삭제
    public boolean memberdelete(int no){
        try {
            String sql = "delete from member where no = "+no+";";
            ps = conn.prepareStatement(sql);
            int count = ps.executeUpdate();
            if(count ==1){
                return true;
            }
        }catch (Exception e){System.out.println(e);}
        return false;
    }

}//class end
