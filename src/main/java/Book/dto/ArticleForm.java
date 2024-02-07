package Book.dto;

import lombok.*;

// 입력 폼 전용 DTO
    // 관례적으로 모든 필드는 직접 접근 권장하지 않는다. private , 안정성보장 , 캡슐화 특징 , setter ,getter,생성자
    // 필드는 private , 생성자 : 빈 , 풀 , 메소드 : toString() , setter , getter
    // 간단한생성자와 toString
    //
@AllArgsConstructor // 컴파일시 모든 필드 생성자를 자동으로 만들어준다. [롬복]
@NoArgsConstructor  // 컴파일시 기본 생성자를 자동으로 만들어준다.     [롬복]
@ToString           // 컴파일시 toString() 자동으로 만들어준다.      [롬복]
@Getter             // 컴파일시 Getter() 자동으로 만들어준다.      [롬복]
@Setter             // 컴파일시 Setter() 자동으로 만들어준다.      [롬복]
public class ArticleForm {
    // 1. 필드
    private String title;   // 입력받은 제목 필드
    private String content; // 입력받은 내용 필드

}
