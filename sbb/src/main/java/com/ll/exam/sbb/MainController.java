package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    private int increaseNo = -1;


    @RequestMapping("/sbb")  //get, post 매핑 모두 포함하는 것 => @RequestMapping
    @ResponseBody
    // 아래 함수의 리턴값을 그대로 브라우저에 표시
    // 아래 함수의 리턴값을 문자열화 해서 브라우저 응답의 바디에 담는다.
    public String index() {
        System.out.println("Hello");  // 콘솔에 출력됨. 서버에서 실행.
        return "안녕하세요!"; // 브라우저에 출력됨. 먼 미래에 브라우저에서 보여짐.
    }

    @GetMapping("/page1")
    @ResponseBody
    public String showPage1() {
        return """
                <form method="POST" action="/page2"> 
                    <input type="number" name="age" placeholder="나이" />
                    <input type="submit" value="page2로 POST 방식으로 이동" />
                </form>               
                """;
    }

    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "0") int age) { //값이 안들어오면 오류나니까 디폴트값을 정할 수 있음.
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요. POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/page2")
    @ResponseBody
    public String showPage2Get(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요. POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/plus")
    @ResponseBody
    public int showPlus(int a, int b) {
        return a + b;
    }

    @GetMapping("/plus2")
    @ResponseBody
    public void showPlus2(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int a = Integer.parseInt(req.getParameter("a"));
        int b = Integer.parseInt(req.getParameter("b"));

        resp.getWriter().append(a + b + "");
    }

    @GetMapping("/minus")
    @ResponseBody
    public int showMinus(int a, int b) {
        return a - b;
    }


    @GetMapping("/increase")
    @ResponseBody
    public int showIncrease() {

        increaseNo++;

        return increaseNo;
    }

    @GetMapping("/gugudan")
    @ResponseBody
    public String showGugudan(Integer dan, Integer limit) { // int는 null 불가능. Integer는 null허용.

        if (limit == null) {
            limit = 9;
        }

        if (dan == null) {
            dan = 9;
        }

        Integer finalDan = dan;
        return IntStream.rangeClosed(1, limit)
                .mapToObj(i -> "%d * %d = %d".formatted(finalDan, i, finalDan * i))
                .collect(Collectors.joining("<br>\n")); //<br>을 기준으로 하나의 문장으로 합친다.

    }

    @GetMapping("/mbti/{name}") // {name} 값 받아오려면 아래에 @PathVariable 쓰면 됨.
    @ResponseBody
    public String showMbti(@PathVariable String name) {

        // switch문 이렇게 사용하면 break 생략 가능. switch 앞에 바로 return 붙일 수 있음.
       return switch ( name ) {
           case "홍길순" -> {
                char j = 'J';
                yield "INF" + "J";
           }
           case "임꺽정" -> "ENFP";
           case "손정아", "홍길동" -> "ENFJ";
           default -> "모름";
       };
    }

}

