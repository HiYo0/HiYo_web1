package hiyoweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.PrimitiveIterator;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class MemberDto {//class start

    private int no;         //회원번호
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private String uuidFile;    // uuid+file

    
//    private String img;       // input type ="text"(String)
    
    private MultipartFile img;  // input type = "file"(MultipartFile) 첨부파일형식

}//class end
