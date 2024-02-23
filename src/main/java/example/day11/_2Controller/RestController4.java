package example.day11._2Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // @Controller + @ResponseBody
@RequestMapping("/day11")   // 해당 클래스내 매핑함수들의 공통URL
public class RestController4 {//class start


    // 1.
    @GetMapping("/ajax1")
    public String ajax1(){
        System.out.println("RestController4.ajax1");
        return "응답";
    }

    // 2. 경로상 변수 활용한 매개변수 요청받기
    @GetMapping("/ajax2/{id}/{content}") // http://localhost/day11/ajax2
    public String ajax2(@PathVariable int id , @PathVariable String content){
        System.out.println("RestController4.ajax2");
        System.out.println("id = " + id+", content = " + content);
        return "응답2";
    }

    // 3. 쿼리 스트링
        // 1.
//    @GetMapping("/ajax3")
//    public String ajax3(int id , String content){
//        System.out.println("RestController4.ajax3");
//        System.out.println("id = " + id+", content = " + content);
//        return "응답3";
//    }
        // 2.
//    @GetMapping("/ajax3")
//    public String ajax3(@RequestParam("id") int id , String content){
//        System.out.println("RestController4.ajax3");
//        System.out.println("id = " + id+", content = " + content);
//        return "응답3";
//    }
        // 3.@RequestParam Map
//    @GetMapping("/ajax3")
//    public String ajax3(@RequestParam Map<String ,String>map){
//        System.out.println("RestController4.ajax3");
//        System.out.println("map = " + map);
//        return "응답3";
//    }
        // 4. DTO
    @GetMapping("/ajax3")
    public String ajax3(AjaxDto dto){
        System.out.println("RestController4.ajax3");
        System.out.println("dto = " + dto);
        return "응답3";
    }

    // 4. HTTP BODY 본문사용
        // 1.
//    @GetMapping("/ajax4")
//    public String ajax4(int id , String content){
//        System.out.println("RestController4.ajax4");
//        System.out.println("id = " + id+", content = " + content);
//        return "응답4";
//    }
        // 2.
    @GetMapping("/ajax4")
    public String ajax4(@RequestParam Map<String,String>map){
        System.out.println("RestController4.ajax4");
        System.out.println("map = " + map);
        return "응답4";
    }


}//class end

