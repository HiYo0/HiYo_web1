package example.day08._2인과제2.day08controller;

import example.day08._2인과제2.day08model.Day08Dao;
import example.day08._2인과제2.day08model.Day08Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Day08controller {//class start

    @Autowired
    Day08Dao dao;

    // 1, 저장
    @PostMapping("_2인과제2/create")
    @ResponseBody
    public boolean create(Day08Dto dto){
        boolean result = dao.create(dto);
        return result;
    }

    // 2. 전체 호출
    @GetMapping("_2인과제2/read")
    @ResponseBody
    public List<Day08Dto> read(){
        List<Day08Dto> result = dao.read();
        return result;
    }

    // 3. 수정
    @PostMapping("_2인과제2/update")
    @ResponseBody
    public boolean update(Day08Dto dto){
        System.out.println("dto = " + dto);
        // 1. 패스워드 검증요청
        boolean result = dao.confirmpassword(dto.getBno(),dto.getBpassword());
        if(result){
            // 2. 수정요청
            result = dao.update(dto);
        }
        return result;
    }


    // 4. 삭제
    @GetMapping("_2인과제2/delete/{bno}/{bpassword}")
    @ResponseBody
    public boolean delete(@PathVariable int bno ,@PathVariable String bpassword){
        System.out.println("bno = " + bno);

        // 1. 패스워드 검증요청
        boolean result = dao.confirmpassword(bno,bpassword);
        if(result) {
            result = dao.delete(bno);
        }
        return result;
    }

    // ================================ view Rest ============================= //
    // 1. HTML ( STATIC ) : http://localhost/day08board.html / REACT
    // 2. 머스테치 ( templates ) 컨트롤의 반환 ( model )

    @GetMapping("/_2인과제2")
    public String boardIndex(){
        return "day08board.html";
    }
}// class end
