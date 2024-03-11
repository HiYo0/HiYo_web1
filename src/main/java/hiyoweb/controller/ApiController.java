package hiyoweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api") // 공통 URL
public class ApiController {//class start

    // 1. 머스테치 요청
    @GetMapping("")
    public String getApi(){
        return "hiyoweb/api/api";
    }
}//class end
