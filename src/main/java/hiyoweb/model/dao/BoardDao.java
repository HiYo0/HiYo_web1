package hiyoweb.model.dao;

import hiyoweb.model.dto.BoardDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

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
    public boolean doUpdateBoard( BoardDto boardDto ){
        Date date =new Date(); // 현재 시간
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("BoardDao.doUpdateBoard");
        try {
            String sql = "update board set btitle = ?,bcontent = ?,bcno=?,bfile=?,bdate=? where bno = "+boardDto.getBno();
            ps = conn.prepareStatement(sql);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2, boardDto.getBcontent());
            ps.setLong(3,boardDto.getBcno());
            ps.setString(4,boardDto.getBfile());
            ps.setString(5,dateFormat.format(date)); // 현재시간
            int count = ps.executeUpdate();
            if (count ==1)return true;

        }catch (Exception e){System.out.println("e = " + e);}

        return false;
    }
    // 5. 글 삭제 처리       delete   /board/delete.do       게시물번호
    public boolean doDeleteBoard(int bno) {
        System.out.println("BoardDao.doDelete");
        try {
            String sql = "delete from board where bno = "+bno;
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            return true;

        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

    // 6. 게시물 작성자 인증
    public boolean boardWriterAuth( long bno , String mid){
        try {
            String sql = "select * from board b inner join member m on b.mno = m.no where b.bno = ? and m.id = ?";
            ps =conn.prepareStatement(sql);
            ps.setLong(1,bno);
            ps.setString(2,mid);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }
    // 7. 댓글 작성 ( brcontent , brindex , mno , bno )
    public boolean postReplyWrite( Map<String , String > map ){
        System.out.println("BoardDao.postReplyWrite");
        try {
            String sql = "insert into breply (brcontent,brindex,mno,bno)" +
                    " values (?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,map.get("brcontent"));
            ps.setString(2,map.get("brindex"));
            ps.setString(3,map.get("mno"));
            ps.setString(4,map.get("bno"));
            int count = ps.executeUpdate();
            if(count ==1){return true;}
        }catch (Exception e){System.out.println("e = " + e);}
        return false;
    }
    // 8. 댓글 출력 ( brno , brcontent ,brdate , brindex , mno ) , 매개변수 : bno
    public List<Map<String ,Object>> getReplyDo( int bno ){
        System.out.println("BoardDao.getReplyDo");
        List<Map<String ,Object >> list = new ArrayList<>(); // 상위댓글 리스트
        try {
            // 상위댓글 먼저 출력       brindex = 0 : 상위댓글
            String sql = "select * from breply where brindex = 0 and bno="+bno;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                // 상위 댓글 하나씩 객체화 하는 곳 =========================//
                // map vs dto
                Map<String , Object > map = new HashMap<>();
                map.put("brno",rs.getString("brno"));
                map.put("brcontent",rs.getString("brcontent"));
                map.put("brdate",rs.getString("brdate"));
                map.put("mno",rs.getString("mno"));
                    // 해당 상위댓글의 하위댓글도 호출하기 =========================//
                String  subSql2 = "select * from breply where brindex = ? and bno="+bno;
                ps= conn.prepareStatement(subSql2);
                ps.setInt(1,Integer.parseInt(rs.getString("brno"))); // index 아니고 brno임
                    // (int) vs Integer.parseInt()
                    // ******* rs 사용하면 안되는 이유 : 현재 상위댓글출력시 rs 사용중(while(rs.next)에서) 이므로
                ResultSet rs2 = ps.executeQuery();
                List<Map<String,Object>> subList = new ArrayList<>(); // 하위댓글 리스트
                while (rs2.next()) {
                    Map<String, Object> subMap = new HashMap<>(); // 댓글답변
                    subMap.put("brno", rs2.getString("brno"));
                    subMap.put("brcontent", rs2.getString("brcontent"));
                    subMap.put("brdate", rs2.getString("brdate"));
                    subMap.put("mno", rs2.getString("mno"));
                    subList.add(subMap);
                }
                    // 해당 상위댓글의 하위댓글도 호출하기 END =========================//
                map.put("subReply",subList); // 하위리스트 추가
                list.add(map);
                // 상위 댓글 하나씩 객체화 하는 곳 END =========================//
                //
            }
            System.out.println("테스트용"+list);
            return list;
        }catch (Exception e){System.out.println("e = " + e);}

        return list;
    }

}//class end
