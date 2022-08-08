package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    //get, post 매핑 모두 포함하는 것 => @RequestMapping
    @RequestMapping("/sbb")
    public void index() {
        System.out.println("index");
    }
}
