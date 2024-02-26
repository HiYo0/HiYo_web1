package hiyoweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {//class start
    @GetMapping("/")
    public String index(){
        return "hiyoweb/index";
    }
}//class end
