package hiyoweb.controller;

import hiyoweb.model.dto.BoardDto;
import hiyoweb.model.dto.BoardPageDto;
import hiyoweb.service.BoardService;
import hiyoweb.service.FileService;
import hiyoweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {//class start

    @Autowired
    private BoardService boardService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;

    // 1. 글쓰기 처리        Post    /board/write.do         Dto
    @PostMapping("/write.do")
    @ResponseBody
    public long doPostBoardWrite(BoardDto boardDto){
        System.out.println("BoardController.doPostBoardWrite");

        // 1. 현재 로그인된 세션 ( 톰캣서버(자바프로그램) 메모리(JVM) 저장소 )
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null){return -2;}

        // 2. 형변환
        String  mid = (String) object;

        // 3. mid를 mno 찾아오기
        long mno = memberService.doGetLoginInfo(mid).getNo();
        boardDto.setMno(mno);

        return boardService.doPostBoardWrite(boardDto);
    }

    // 2. 전체 글 출력 호출    Get     /board/do       현재페이지 , 페이징처리 , 검색
    @GetMapping("/do")
    @ResponseBody                           // 쿼리 스트링
    public BoardPageDto doGetBoardViewList(@RequestParam int page
            , @RequestParam int pageBoardSize, @RequestParam int bcno
            , @RequestParam String key,@RequestParam String keyword){
        System.out.println("BoardController.doGetBoardViewList");

        return boardService.doGetBoardViewList(page , pageBoardSize ,bcno , key,keyword);
    }

    // 3. 개별 글 출력 호출    Get     /board/view.do        게시물 번호
    @GetMapping("/view.do")
    @ResponseBody
    public BoardDto doGetBoardView( @RequestParam int bno){
        System.out.println("BoardController.doGetBoardView");

        return boardService.doGetBoardView(bno);
    }

    // 4. 글 수정 처리         put    /board/update.do       DTO
        // 매개변수 : bno , btitle , bcontent , uploadfile , bcno
    @PutMapping("/update.do")
    @ResponseBody
    public boolean doUpdateBoard( BoardDto boardDto ){
        System.out.println("BoardController.doUpdateBoard");
        System.out.println("boardDto = " + boardDto);
        // 유효성검사.
            // 1. 현재 로그인된 아이디. (세션)
        Object object = request.getSession().getAttribute("loginDto");
        if(object != null){
            String mid = (String)object;
            boolean result = boardService.boardWriterAuth( boardDto.getBno() , mid ); // 해당 세션정보가 작성한 글인지 체크.
            if (result){
                return boardService.doUpdateBoard( boardDto );
            }
        }
        return false;
    }

    // 5. 글 삭제 처리       delete   /board/delete.do       게시물번호
    @DeleteMapping("delete.do")
    @ResponseBody
    public boolean doDeleteBoard(int bno){
        System.out.println("BoardController.doDelete");

        // 유효성검사.
        // 1. 현재 로그인된 아이디. (세션)
        Object object = request.getSession().getAttribute("loginDto");
        if(object != null){
            String mid = (String)object;
            boolean result = boardService.boardWriterAuth( bno , mid ); // 해당 세션정보가 작성한 글인지 체크.
            if (result){
                return boardService.doDeleteBoard( bno );
            }
        }

        return boardService.doDeleteBoard(bno);
    }


    @Autowired
    private FileService fileService;

    // 6. 다운로드 처리 ( 함수만들때 고민할점. 1.매개변수 : 파일명  2.반환 3.사용처 : get http요청 )
    @GetMapping("/file/download")
    @ResponseBody
    public void getBoardFileDownload( @RequestParam String bfile ){
        fileService.fileDownload( bfile );
    }

    // 7. 댓글 작성 ( bno , brcontent , brindex[0: 상위 , 1~:하위] , mno )
    @PostMapping("/reply/write.do")
    @ResponseBody
                                // @RequestParam 필수 ? 보내줄때 키 이름이 없어서
    public boolean postReplyWrite(@RequestParam Map<String , String > map ){
        System.out.println("BoardController.postReplyWrite");
        System.out.println("map = " + map);

        // 1. 현재 로그인된 세션 ( 톰캣서버(자바프로그램) 메모리(JVM) 저장소 )
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null){return false;} // 세션없다(로그인 안했다)
        System.out.println(object);

        // 2. 형변환
        String  mid = (String) object;

        // 3. mid를 mno 찾아오기
        long mno = memberService.doGetLoginInfo(mid).getNo();
        // 4. map에 mno넣기
        map.put("mno",mno+"");

        System.out.println("map = " + map);

        return boardService.postReplyWrite(map);
    }

    // 8. 댓글 출력 ( brno , brcontent ,brdate , brindex , mno ) , 매개변수 : bno
    @GetMapping("/reply/do")
    @ResponseBody
    public List<Map<String ,Object>> getReplyDo( int bno ){
        System.out.println("BoardController.getReplyDo");

        return boardService.getReplyDo(bno);
    }




    // ===================================================================== //

    // 1. 글쓰기 페이지 이동            Get /board/write
    @GetMapping("/write")
    public String getBoardWrite(){
        System.out.println("BoardController.getBoardWrite");
        return "hiyoweb/board/write";
    }

    // 2. 게시판 페이지 이동            Get /board
    @GetMapping("/")
    public String getBoard(){
        System.out.println("BoardController.getBoard");
        return "hiyoweb/board/board";
    }

    // 3. 게시판 상세 페이지 이동       Get /board/view
    @GetMapping("/view")
    public String getBoardView(){
        return "hiyoweb/board/view";
    }

    // 4. 글 수정 페이지 이동           Get /board/update
    @GetMapping("/update")
    public String getBoardUpdate(){
        return "hiyoweb/board/update";
    }
}
