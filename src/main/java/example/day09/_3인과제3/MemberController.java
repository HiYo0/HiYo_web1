package example.day09._3인과제3;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    MemberDao dao;

    //전체페이지요청
    @GetMapping("/member") // http://localhost/member
    public String pageCall() {
        System.out.println("pageCall 실행됨");
        return "/day09Member.html";
    }

    //전체페이지호출
    @GetMapping("/member/read")
    @ResponseBody
    public List<MemberDto> read() {
        List<MemberDto> result = dao.read();
        return result;
    }

    //등록처리
    @PostMapping("/member/insert")
    @ResponseBody
    public boolean creat(MemberDto dto) {
        boolean result = dao.creat(dto);
        return result;
    }

    //수정처리
    @PostMapping("/member/update")
    @ResponseBody
    public boolean edit(MemberDto dto) {

        boolean result = dao.edit(dto);

        return result;
    }

    //삭제처리
    @GetMapping("/member/delete")
    @ResponseBody
    public boolean memberDelete(MemberDto dto) {
        boolean result = dao.memberDelete(dto);
        return result;
    }

//    // salary페이지 호출
//    @GetMapping("/salary")
//    public String salaryPageCall(){
//        return "salary.html";
//    }

    // salary페이지요청
    @GetMapping("/salary/read") // http://localhost/salary/read
    @ResponseBody
    public List<SalaryDto> salaryRead(SalaryDto sdto){
        System.out.println(sdto);
        List<SalaryDto> result = dao.salaryRead(sdto);
        return result;
    }

    // 급여등록처리
    @PostMapping("/salary/insert")
    @ResponseBody
    public boolean salaryCreat(SalaryDto dto){
        boolean result = dao.salaryCreat(dto);
        return result;
    }



}
