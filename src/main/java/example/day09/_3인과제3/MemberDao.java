package example.day09._3인과제3;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class MemberDao {

    // DB연동
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    public MemberDao(){
        try {
            // 1. mysql JDBC 호출 ( 각 회사별  상이 , 라이브러리 다운로드 )
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 해당 db서버의 주소와 db연동
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day9member", "root", "1234");
            System.out.println("DB됨");
        }catch (Exception e ){   System.out.println(e); }
    }// DB연동 END

    // 페이지 용 리스트 불러오기
    public List<MemberDto> read(){
        List<MemberDto> list = new ArrayList<>();
        try {
            String sql = "select * from member;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                MemberDto dto = new MemberDto(
                  rs.getInt(1),
                  rs.getString(2),
                  rs.getString(3)
                );
                list.add(dto);
            }
            return list;


        }catch (Exception e){System.out.println("e = " + e);}

        return null;
    }

    // 등록처리
    public boolean creat(MemberDto dto){
        try {
            String sql = "insert into member values (0,?,?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getMname());
            ps.setString(2, dto.getMphone());
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    }

    // 수정처리
    public boolean edit(MemberDto dto){
        try {
            String sql = "update member set mphone = ? where mno = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getMphone());
            ps.setInt(2, dto.getMno());
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    } // 수정처리 END

    // 삭제처리
    public boolean memberDelete(MemberDto dto){
        try {
            String sql = "delete from member where mno = "+ dto.getMno()+";";
            ps = conn.prepareStatement(sql);
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    }

    // 페이지자료요청
    public List<SalaryDto> salaryRead(SalaryDto sdto){
        List<SalaryDto> list = new ArrayList<>();
        try {
            String sql = "select * from salary where mno = "+sdto.getMno()+";";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                SalaryDto salaryDto = new SalaryDto(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                list.add(salaryDto);
            }
            return list;

        }catch (Exception e){System.out.println("e = " + e);}

        return null;
    }

    // 등록처리
    public boolean salaryCreat(SalaryDto dto) {
        try {
            String sql = "insert into salary values (0,?,?,?);";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, dto.getMno());
            ps.setString(2, dto.getScontent());
            ps.setInt(3,dto.getSalary());
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){System.out.println("e = " + e);}

        return false;
    }

}//class end

