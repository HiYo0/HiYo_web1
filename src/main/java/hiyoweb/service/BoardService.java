package hiyoweb.service;

import hiyoweb.model.dao.BoardDao;
import hiyoweb.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    // 2. 전체 글 출력 호출    Get     /board.do             X , 페이징처리 , 검색

    // 3. 개별 글 출력 호출    Get     /board/view.do        게시물 번호
    public BoardDto doGetBoardView(@RequestParam int bno){
        System.out.println("BoardService.doGetBoardView");
        return boardDao.doGetBoardView(bno);
    }

    // 4. 글 수정 처리         put    /board/update.do       DTO

    // 5. 글 삭제 처리       delete   /board/delete.do       게시물번호

}//class end
