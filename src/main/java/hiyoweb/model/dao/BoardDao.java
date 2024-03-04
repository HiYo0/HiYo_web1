package hiyoweb.model.dao;

import hiyoweb.model.dto.BoardDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Statement;

@Component
public class BoardDao extends Dao {
    // 1. 글쓰기 처리[ 글쓰기를 성공했을때 자동 생성된 글번호 반환 , 실패시 0 ]        Post    /board/write.do         Dto
    public long doPostBoardWrite( BoardDto boardDto){
        System.out.println("BoardDao.doPostBoardWrite");
        System.out.println("boardDto = " + boardDto);

        try {
            String sql = "insert into board(btitle ,bcontent,bfile,mno,bcno)value(?,?,?,?,?);";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2, boardDto.getBcontent());
            ps.setString(3, boardDto.getBfile());
            ps.setLong(4,boardDto.getMno());
            ps.setLong(5,boardDto.getBcno());
            int count = ps.executeUpdate();
            if(count==1){
                rs = ps.getGeneratedKeys();
                if(rs.next()){return rs.getLong(1);}
            }
        }catch (Exception e){System.out.println("e = " + e);}
        return 0;
    }

    // 2. 전체 글 출력 호출    Get     /board.do             X , 페이징처리 , 검색


    // 3. 개별 글 출력 호출    Get     /board/view.do        게시물 번호
    public BoardDto doGetBoardView(int bno){
        System.out.println("BoardDao.doGetBoardView");
        System.out.println("bno = " + bno);
        BoardDto boardDto = null;
        try {
            String sql = "select * from board where bno = ?;";
            ps= conn.prepareStatement(sql);
            ps.setLong(1,bno);
            rs = ps.executeQuery();
            if(rs.next()){
                boardDto = new BoardDto(
                  rs.getLong("bno"),
                        rs.getString("btitle"),
                        rs.getString("bcontent"),
                        rs.getString("bfile"),
                        rs.getLong("bview"),
                        rs.getString("bdate"),
                        rs.getLong("mno"),
                        rs.getLong("bcno"),
                        null
                );
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }

        return boardDto;
    }

    // 4. 글 수정 처리         put    /board/update.do       DTO

    // 5. 글 삭제 처리       delete   /board/delete.do       게시물번호

}//class end
