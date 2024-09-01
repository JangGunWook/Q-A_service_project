package com.sbs.exam.sbb;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


//@Controller : 스필부트한테 해당 클래스는 컨트롤러 역할이라고 알려준다

@Controller
public class HomeController {

    private int increaseNo;
    private List<Article> articles;



    public HomeController() { //생성자

        increaseNo = -1;
        articles = new ArrayList<>();

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

    @GetMapping("/home/returnMap")
    @ResponseBody
    public Map<String, Object> showRetrunMap(){

        // HashMap<>()같은 경우 순서를 보장할 수 없음
        // LinkedHashMap<>() 순서를 보장할 수 있음
        Map<String, Object> map = new LinkedHashMap<>(){{
            put("id",1);
            put("age",5);
            put("name", "푸바오");
            put("related", new ArrayList<>(){{
                add(2);
                add(3);
                add(4);
                add(5);
            }});
        }};
        return map;
    };


    @GetMapping("/home/returnMapAnimal2")
    @ResponseBody
    public Animal2 showRetrunAnimal2(){

        Animal2 animal2 = new Animal2(1, 20,"포비", new ArrayList<>(){{
            add(2);
            add(33);
            add(44);

        }});
        animal2.setName(animal2.getName() + "v2");
        return animal2;
    };

    @GetMapping("/home/returnAnimalMapList")
    @ResponseBody
    public List<Map<String ,Object>> ShowreturnAnimalMapList(){

        Map<String, Object> map1 = new LinkedHashMap<>(){{
            put("id",1);
            put("age",5);
            put("name", "푸바오");
            put("related", new ArrayList<>(){{
                add(2);
                add(3);
                add(4);
                add(5);
            }});
        }};

        Map<String, Object> map2 = new LinkedHashMap<>(){{
            put("id",1);
            put("age",5);
            put("name", "찐따오");
            put("related", new ArrayList<>(){{
                add(2);
                add(4);
                add(5);
                add(6);
            }});
        }};

        List<Map<String ,Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);

        return list;

    };

    @GetMapping("addArticle")
    @ResponseBody
    public String addArticle(String title, String body){
        Article article = new Article(title, body);

        //HomeController에서 생성한 초기 생성자 Article class의 기본생성자를
        articles.add(article);
        System.out.println(article);
        //System.out.println(Article.lastId);

        return "%d번 게시물이 추가되었습니다.".formatted(article.getId());
    }


    @GetMapping("/article/list")
    @ResponseBody
    public List<Article> getArticles(){

        // HomeController 시작시 생성자에 넣어 놓은 List<Article> articles 이다
        return articles;
    }


    @AllArgsConstructor //생성자 만들기
    @Data //getter,seter생성
    class Animal{
        private final int id;

        private final int age;

        private final String name;

        private final List<Integer> related; //식별번호
    };

    @AllArgsConstructor //생성자 만들기
    @Data //getter,seter생성
    class Animal2{
    private final int id;

    private final int age;

    private  String name;

    private  List<Integer> related; //식별번호
    };


    @AllArgsConstructor
    @Data
    class Article{

         static int lastId; //static 서버내 올라가있는 값


        private final int id;
        private final String title;
        private final String body;

        // 프로그램이 시작할때 담고 있는 데이터의 기본값
        static {
            lastId = 0;
        }

        public Article(String title, String body){
            this(++lastId, title, body);
        }

        }
};
