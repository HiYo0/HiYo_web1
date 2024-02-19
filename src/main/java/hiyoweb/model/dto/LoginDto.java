package hiyoweb.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString


public class LoginDto {//class start
    private int no;// 회원번호
    private String id;
    private String pw;
}//class end
