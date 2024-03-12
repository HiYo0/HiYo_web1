package hiyoweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@AllArgsConstructor@NoArgsConstructor@Setter@Getter@ToString@Builder
public class ProductDto {//class start
    private int pno;	            // 제품번호
    private String pname; 			// 제품 이름
    private int pprice; 			// 제품 가격
    private String pcontent;		// 제품 설명
    private int pstate; 	    	// 제품 상테
    private String pdate;       	// 제품 등록일
    private String plet; 			// 제품 위치 경도
    private String plng;  			// 제품 위치 위도
    private int mno; 				// 등록한 사람(회원FK)

    // - 등록할때 이미지
    private List<MultipartFile> uploadFiles;
    // - 출력할때 이미지
    private List< String > pimg;
    // - 출력시 작성자번호가 아닌 작성자 아이디
    private String mid;

    // 1. 제품 등록 [ pname , pprice , pcontent , plet , plng , mno(세션) ]
    
    // 2. 제품 출력 [ pno , pname , pprice , pstate , plet , plng ]

    // 3. 제품 지도에서 마커 글릭시 상세 출력 [ pno , pname , pprice , pcontent , pstate, pdate , plet , plng , mid , pimg ]
}//class end
