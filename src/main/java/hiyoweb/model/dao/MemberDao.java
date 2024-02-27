package hiyoweb.model.dao;

import hiyoweb.model.dto.LoginDto;
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
                MemberDto memberDto = new MemberDto();
                        memberDto.setNo(rs.getInt(1));
                        memberDto.setId(rs.getString(2));
                        memberDto.setPw(rs.getString(3));
                        memberDto.setName(rs.getString(4));
                        memberDto.setEmail(rs.getString(5));
                        memberDto.setPhone(rs.getString(6));
                        memberDto.setUuidFile(rs.getString(7));

                return memberDto;
            }
        }catch (Exception e){System.out.println(e);}
        return null;
    }
    // 회원 ID로 회원정보 return 받기.
    public MemberDto memberNo(LoginDto loginDto){
        System.out.println("loginDto 정보받기 = " + loginDto);
        try {
            String sql ="select * from member where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1,loginDto.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                MemberDto memberNo1 = new MemberDto();
                    memberNo1.setNo(rs.getInt(1));
                    memberNo1.setId(rs.getString(2));
                    memberNo1.setPw(rs.getString(3));
                    memberNo1.setName(rs.getString(4));
                    memberNo1.setEmail(rs.getString(5));
                    memberNo1.setPhone(rs.getString(6));
                    memberNo1.setUuidFile(rs.getString(7));

                return memberNo1;

            }


        }catch (Exception e){System.out.println("e = " + e);}
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
            ps.setString(5,dto.getUuidFile());
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

    // 회원가입
    public boolean signup(MemberDto dto){
        try {
            String sql = "insert into member value (0,?,?,?,?,?,?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1,dto.getId());
            ps.setString(2,dto.getPw());
            ps.setString(3,dto.getName());
            ps.setString(4,dto.getEmail());
            ps.setString(5,dto.getPhone());
            ps.setString(6,dto.getUuidFile());
            int count = ps.executeUpdate();
            if(count==1){return true;}
        }catch (Exception e){System.out.println(e);return false;}
        return false;
    }

    // 로그인
    public boolean login(LoginDto dto){
        System.out.println("DAO 처리 dto = " + dto);
        try {
            String sql = "select * from member where id = '"+dto.getId()+"' and pw = '"+dto.getPw()+"';";
            ps = conn.prepareStatement(sql);
//            ps.setString(1, dto.getId());
//            ps.setString(2, dto.getPw());
            rs = ps.executeQuery();
            if(rs.next()){return true;}
        }catch (Exception e){System.out.println(e);}
        return false;
    }

    // 중복검사
    public boolean loginCell(MemberDto memberDto){
        String sql ="";
        try {
            sql = "select * from member where id = "+memberDto.getId()+";";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()) return true;

        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    }

}//class end
