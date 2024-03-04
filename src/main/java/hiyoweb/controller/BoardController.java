package hiyoweb.controller;

import hiyoweb.model.dto.BoardDto;
import hiyoweb.service.BoardService;
import hiyoweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    // 2. 전체 글 출력 호출    Get     /board.do             X , 페이징처리 , 검색

    // 3. 개별 글 출력 호출    Get     /board/view.do        게시물 번호
    @GetMapping("/view.do")
    @ResponseBody
    public BoardDto doGetBoardView( @RequestParam int bno){
        System.out.println("BoardController.doGetBoardView");
        return boardService.doGetBoardView(bno);
    }

    // 4. 글 수정 처리         put    /board/update.do       DTO

    // 5. 글 삭제 처리       delete   /board/delete.do       게시물번호

    // ==================================================== //

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

}
