package com.sbs.exam.sbb;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;


//@Controller : 스필부트한테 해당 클래스는 컨트롤러 역할이라고 알려준다

@Controller
public class HomeController {

    private int increaseNo;

    public HomeController() { //생성자

        increaseNo = -1;

    }

    @RequestMapping("/sbb")
    //@ResponseBody
    //아래 함수의 리턴갑서을 그대로 브라우저에 표시
    //아래 함수의 리턴값을 문자열화 해서 브라우저 응답을 바디에 담는다.

    @ResponseBody
    public String ShowHome(){
        return "SBB 요청합니다";
    }


    @RequestMapping ("/test")

    @ResponseBody
    public String ShowTest(){
        return """
                <h1>안녕하세요. </h1>
                <input type ="text" placeholder = "입력하세요"/>
                """;
    }


    @GetMapping("/page1")

    @ResponseBody
    public String ShowGet(){
        return """
                <form method = "POST" action= "/page2">
                    <input type="number" name = "age" placeholder = "나이를 입력하세요">
                    <input type="submit" value= "page2로 POST방식으로 이동"/>
                </form>
                <br>
                <form method = "GET" action= "/page2">
                    <input type="number" name = "age" placeholder = "나이를 입력하세요">
                    <input type="submit" value= "page2로 GET방식으로 이동"/>
                </form>
                """;
    }



    @GetMapping("/page2")

    @ResponseBody
    public String ShowPage2Get(@RequestParam(defaultValue = "0")int age){
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요. Get방식으로 오신걸 환영합니다.</h>
                """.formatted(age);
    }

    @PostMapping("/page2")

    @ResponseBody
    public String ShowPage2Post(@RequestParam(defaultValue = "0") int age){
                                    // input태그 name값 ※ 만약 age값이 아무것도 없으면 에러가 발생한다 그래서 defualt값을 넣어줌
                                    // 거기서 사용하는 것이 @RequestParam(defaultValue = "")이다
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요. Post방식으로 오신걸 환영합니다.</h>
                """.formatted(age);
    }

    @GetMapping("/plus")

    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0")int a, @RequestParam(defaultValue = "0")int b){
        return a + b;
    }

    @GetMapping("/increase")

    @ResponseBody
    public int increae(){
        //increaseNo를 전역 변수로 선언한다! private를 사용하여 HomeController에서만 활용가능하도록 함
        //전역변수로 선언한  increaeNo를 기본생성자를 통해서 기본값을 초기화 해준다!
        // increaeNo = -1
        // 위 increase페이지로 이동할때마다 +1씩 증가하도록함

        increaseNo++;
        return increaseNo;
    };

    @GetMapping("/gugudan")
    @ResponseBody
    public String gugudan(Integer dan, Integer limit){
        //increaseNo를 전역 변수로 선언한다! private를 사용하여 HomeController에서만 활용가능하도록 함
        //전역변수로 선언한  increaeNo를 기본생성자를 통해서 기본값을 초기화 해준다!
        // increaeNo = -1
        // 위 increase페이지로 이동할때마다 +1씩 증가하도록함

        if(dan == null){
            dan = 1;
        }

        if(limit == null){
            limit = 9;
        }
        //final 선언 : 상수선언(상수 = 변하지 않는값)
        final Integer FinalDan = dan;

        return IntStream.rangeClosed(1 , limit)//1~limit
                .mapToObj(i->"%d * %d = %d".formatted(FinalDan, i ,FinalDan * i))
                .collect(Collectors.joining("<br>"));

    };


    //★세션
    @GetMapping("/saveSession/{name}/{value}")
    @ResponseBody
    public String saveSession(@PathVariable String name, @PathVariable String value , HttpServletRequest req){

        HttpSession session = req.getSession();

        session.setAttribute(name, value);

        return "세션변수의 %s의 값이 %s(으)로 설정되었습니다.".formatted(name,value);
    };
    //설정한 session값 가져오기
    @GetMapping("/getSession/{name}")
    @ResponseBody
    public String saveSession(@PathVariable String name,HttpSession session) {

        String value = (String) session.getAttribute(name);

        return "세션변수의 %s의 값이 %s(으)로 설정되었습니다.".formatted(name, value);
    };

    }
