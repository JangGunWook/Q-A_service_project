package com.sbs.exam.sbb;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//@Controller : 스필부트한테 해당 클래스는 컨트롤러 역할이라고 알려준다

@Controller
public class HomeController {

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
}
