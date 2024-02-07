package example.day03.WebMvc;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@ToString@Getter@Setter
public class TodoDto {
    // 1. 필드 ( dto로 사용할 셩우 db table 필드와 일치하고 추가적으로 추가가능 )
    private int id;
    private String content;
    private String deadline;
    private boolean state;

}
