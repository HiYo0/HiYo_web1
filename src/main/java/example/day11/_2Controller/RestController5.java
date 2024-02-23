package example.day11._2Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/day11")
public class RestController5 {//class start
// ============= 경로상의변수(쿼리스트링) : <POST , PUT , GET , DELETE >==== //
    // 글등록, 글조회 , 글삭제 , 글수정 => GET 다 가능
    // 쿼리스트링 : URL 상에 데이터/매개변수 가 표시됨.
    // 캐시(기록)남기 => 장점 : 빠르다 , 단점 :노출

// ======================= contentType : from <POST , PUT>======================= //
    // URL 상에 데이터/매개변수 표시안함. => HTTP body(본문) 이용 , POST/PUT 가능

    
    // 1.
//    @PostMapping("/ajax5")
//    public String ajax5(int id , @RequestParam String content){
//        System.out.println("RestController5.ajax5");
//        System.out.println("id = " + id+", content = " + content);
//
//        return "응답5";
//    }
    // 2. MAP
//    @PostMapping("/ajax5")
//    public String ajax5(@RequestParam Map<String ,String>map){
//        System.out.println("RestController5.ajax5");
//        System.out.println("map = " + map);
//
//        return "응답5";
//    }
    // 2. Dto
    @PostMapping("/ajax5")
    public String ajax5(AjaxDto dto){
        System.out.println("RestController5.ajax5");
        System.out.println("dto = " + dto);

        return "응답5";
    }
// ==================================================================== //
// ======================= contentType : application/json ======================= //
//    @PostMapping("/ajax6")
//    public String ajax6(@RequestBody AjaxDto dto){
//        System.out.println("RestController5.ajax6");
//        System.out.println("dto = " + dto);
//
//        return "응답6";
//    }

    @PostMapping("/ajax6")
    public String ajax6(@RequestBody Map<String,String>map){
        System.out.println("RestController5.ajax6");
        System.out.println("map = " + map);

        return "응답6";
    }

// ==================================================================== //

}//class end
