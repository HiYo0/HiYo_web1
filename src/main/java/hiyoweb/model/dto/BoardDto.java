package hiyoweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter@ToString
public class BoardDto {
    private long bno;
    private String btitle;
    private String bcontent;
    private String bfile;
    private long bview;
    private String bdate;
    private long mno;
    private long bcno;
    
    private MultipartFile uploadfile;   // 실제첨부파일
}
/*
    글쓰기용
        - 입력받기 : btitle , bcontent , uploadfile , bcno
        - 서버처리 : bno자동 bview 기본값0 bdate 기본값현재날짜 mno로그인(*세션)

    글출력용
*/
