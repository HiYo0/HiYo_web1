package hiyoweb.service;

import hiyoweb.model.dao.BoardDao;
import hiyoweb.model.dto.BoardDto;
import hiyoweb.model.dto.BoardPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;
    @Autowired
    private FileService fileService;

    // 1. 글쓰기 처리        Post    /board/write.do         Dto
    public long doPostBoardWrite(BoardDto boardDto){
        System.out.println("BoardService.doPostBoardWrite");

        // 1. 첨부파일처리
        if(!boardDto.getUploadfile().isEmpty()){ // 첨부파일이 있으면
            String fileName =  fileService.fileUpload(boardDto.getUploadfile());
            if(fileName != null){// 업로드 성공했으면
                boardDto.setBfile(fileName); // DB저장할 첨부파일명 대입
            }else { return -1;}
        }
        // 2. DB처리
        return boardDao.doPostBoardWrite(boardDto);
    }

    // 2. 전체 글 출력 호출    Get     /board.do             현재페이지 , 페이징처리 , 검색
    public BoardPageDto doGetBoardViewList(int page , int pageBoardSize
            , int bcno ,String key ,String keyword){
        System.out.println("BoardService.doGetBoardViewList");

        // 페이지처리시 사용할 SQL 구문 : limit 시작레코드번호(0부터) , 출력개수
        // 1. 페이지당 게시물을 출력할 개수
//        int pageBoardSize = 2;

        // 2. 페이지당 게시물을 출력할 시작 레코드번호.
        int startRow = (page-1)*pageBoardSize;
        // 3. 총 페이지수
            // 1. 전체 게시물수
        int totalBoardSize = boardDao.getBoardSize(bcno , key , keyword);
            // 2. 총페이지수 계산
        int totalPage = totalBoardSize % pageBoardSize == 0 ?
                    // 나머지가 없으면 그냥계산 있으면 +1
                totalBoardSize / pageBoardSize :
                totalBoardSize / pageBoardSize +1 ;
        // 4. 게시물 정보 요청
        List<BoardDto> list = boardDao.doGetBoardViewList(startRow,pageBoardSize,bcno ,key , keyword);

        // 5. 페이징버튼
            // 1. 페이지버튼 최대 개수
        int btnSize = 5;        // 5개씩
            // 2. 페이지버튼 시작번호
        int startBtn = (page-1)/btnSize*btnSize+1;

        // 3. 페이지버튼 끝번호
        int endBtn = startBtn+btnSize-1;

            // 만약에 페이지버튼의 끝번호가 총페이지수 보다는 커질수 없다.
        if( endBtn >= totalPage )endBtn = totalPage;

        // pageDto 구성 * 빌버패턴 : 생성자의 단덤 ( 매개변수에 따른 유연성 부족 )을 보완
        // new 연산자 없이 builder() ㅎ마수 이용한 객체 생성 라이브러리 제공
        // 사용방법 : 클래스명.builder().필드명( 대입값 ).필드명( 대입값 ).build();
        // + 생성자 보단 유연성 : 매개변수의 순서 와 개수 자유롭다.
            // 빌더패턴 vs 생성자 vs setter 중 선택해서 사용
//        BoardPageDto boardPageDto = new BoardPageDto( page , totalPage , startBtn , endBtn , list );
        BoardPageDto boardPageDto = BoardPageDto.builder()
                .page(page)
                .totalBoardSize(totalBoardSize)
                .totalPage(totalPage)
                .startBtn(startBtn)
                .endBtn(endBtn)
                .list(list)
                .build();// 끝날때는 .build() 로 마무리

        return boardPageDto;
    }

    // 3. 개별 글 출력 호출    Get     /board/view.do        게시물 번호
    public BoardDto doGetBoardView(int bno){
        System.out.println("BoardService.doGetBoardView");

        // 조회수 추가시키기
        boardDao.boardViewIncrease(bno);

        return boardDao.doGetBoardView(bno);
    }

    // 4. 글 수정 처리         put    /board/update.do       DTO
    public boolean doUpdateBoard( BoardDto boardDto ){
        System.out.println("BoardService.doUpdate");

        // 1. 기존 첨부파일명 구하고
        String bfile = boardDao.doGetBoardView((int) boardDto.getBno()).getBfile();

        // - 새로운 첨부파일이 있다. 없다.
        if(!boardDto.getUploadfile().isEmpty()){ // 수정시 새로운 첨부파일이 있으면

            // 새로운 첨부파일을 업로드 하고 기존 첨부파일 삭제
            String fileName = fileService.fileUpload(boardDto.getUploadfile());
            if(fileName != null){   // 업로드 성공
                boardDto.setBfile(fileName); // 새로운 첨부파일의 이름 dto 대입
                // 기존 첨부파일 삭제.
                    // 1. 위에씀 bfile
                    // 2. 기존 첨부파일 삭제
                fileService.filerDelete(bfile);
            }else {return false;} // 업로드 실패
        }else { // 기존파일이 없다면
            // 업로드 할 필요 없다.
            // 기존 첨부파일명을 그대로 대입.
            boardDto.setBfile((bfile)); // 새로운 첨부파일이 없으면 기존 첨부파일명을 대입
        }
        return boardDao.doUpdateBoard(boardDto);
    }

    // 5. 글 삭제 처리       delete   /board/delete.do       게시물번호
    public boolean doDeleteBoard(int bno){
        System.out.println("BoardService.doDelete");

        // 게시판 정보 호출
            // - 레코드 삭제 하기전에 삭제할 첨부파일명을 임시로 꺼내둔다
        String bfile = boardDao.doGetBoardView(bno).getBfile();

        // 1. DAO 처리
        boolean result = boardDao.doDeleteBoard(bno);

        // 2. DAO 처리 성공시 첨부파일도 삭제
        if(result){
            System.out.println("bfile = " + bfile);
            if(bfile !=null) { // 기존에 첨부파일이 있었으면
                fileService.filerDelete(bfile); // 미리 꺼내둔 삭제할 파일명 대입해서 삭제.
            }
        }
        return result;
    }

    // 6. 게시물 작성자 인증
    public boolean boardWriterAuth( long bno , String mid){
        System.out.println("BoardService.boardWriterAuth");

        return boardDao.boardWriterAuth(bno,mid);
    }

    // 7. 댓글 작성 ( brcontent , brindex , mno , bno )
    public boolean postReplyWrite( Map<String , String > map ){
        System.out.println("BoardService.postReplyWrite");

        return boardDao.postReplyWrite(map);
    }
    // 8. 댓글 출력 ( brno , brcontent ,brdate , brindex , mno ) , 매개변수 : bno
    public List<Map<String ,Object>> getReplyDo( int bno ){
        System.out.println("BoardService.getReplyDo");

        return boardDao.getReplyDo(bno);
    }



}//class end
