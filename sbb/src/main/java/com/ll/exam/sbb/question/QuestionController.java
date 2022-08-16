package com.ll.exam.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor // 생성자 주입
public class QuestionController {
    //@Autowired // 필드주입
    //여기에 final 붙이고 @RequiredArgsConstructor 붙여주면 final 붙은것들은 자동으로 @Autowired가 됨.
    private final QuestionRepository questionRepository;


    @RequestMapping("/question/list")
    // 이 자리에 @ResponseBody가 없으면 resources/question_list/question_list.html 파일을 뷰로 삼는다.
    public String list() {

        return "question_list";
    }

    @RequestMapping("/question/list2")
    @ResponseBody
    public String list2() {
        return "리스트2 입니다!!!";
    }
}