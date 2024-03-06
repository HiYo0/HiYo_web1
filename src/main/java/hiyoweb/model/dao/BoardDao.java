package hiyoweb.model.dao;

import hiyoweb.model.dto.BoardDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    // 2-1. 전체 글 출력 호출    Get     /board.do             X , 페이징처리 , 검색
    public List<BoardDto> doGetBoardViewList(int startRow, int pageBoardSize ,int bcno, String key , String keyword) {
        System.out.println("BoardDao.doGetBoardViewList");
        List<BoardDto> list = new ArrayList<>();
        BoardDto boardDto = null;
        try {
            // sql 앞부분
            String sql = "select * from board b inner join member m on b.mno = m.no";
            // sql 중간부분
            if(bcno>0){sql += " where bcno="+bcno;}
            if(!keyword.isEmpty()){
                sql += bcno>0?" and ":" where "+key+" like '%"+keyword+"%'";
            }
            // sql 뒷부분
            sql += " order by b.bdate desc limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,startRow);
            ps.setInt(2,pageBoardSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                boardDto = new BoardDto(
                        rs.getLong("bno"),
                        rs.getString("btitle"),
                        rs.getString("bcontent"),
                        rs.getString("bfile"),
                        rs.getLong("bview"),
                        rs.getString("bdate"),
                        rs.getLong("mno"),
                        rs.getLong("bcno"),
                        null,
                        rs.getString("id"),
                        rs.getString("img")
                );
                list.add(boardDto);

            }// while end
            return list;
        }catch(Exception e) {System.out.println("e = " + e);}

        return list;

    }// method end
    // 2-2. 현재 전체 게시물 수 호출
    public int getBoardSize( int bcno , String key , String keyword ){
        System.out.println("DAO 입력받은거 bcno = " + bcno + ", key = " + key + ", keyword = " + keyword);
        try {
            String sql = "select count(*) from board b inner join member m on b.mno = m.no ";

            // 총레코드 수
            if(bcno>0){sql+=" where bcno="+bcno;}
            if(!keyword.isEmpty()){
                System.out.println("검색키워드 존재함!");
                sql += bcno>0?" and ":" where "+key+" like '%"+keyword+"%'";
            }

            ps=conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){return rs.getInt(1);}

        }catch (Exception e){System.out.println("e = " + e);}
        return 0;
    }


    // 3-1. 개별 글 출력 호출    Get     /board/view.do        게시물 번호
    public BoardDto doGetBoardView(int bno){
        System.out.println("BoardDao.doGetBoardView");
        System.out.println("bno = " + bno);
        BoardDto boardDto = null;
        try {
            String sql = "select * from board b inner join member m on b.mno = m.no where b.bno = ?;";
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
                        null,
                        rs.getString("id"),
                        rs.getString("img")
                );
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        System.out.println("DAO에서 boardDto = " + boardDto);

        return boardDto;
    }
    // 3-2. 개별글 출력시 조회수 증가
    public void boardViewIncrease(int bno){
        try {
            String sql = "update board set bview = bview+1 where bno ="+bno;
            ps= conn.prepareStatement(sql);
            ps.executeUpdate();

        }catch (Exception e){System.out.println("e = " + e);}
    }


    // 4. 글 수정 처리         put    /board/update.do       DTO

    // 5. 글 삭제 처리       delete   /board/delete.do       게시물번호

}//class end
