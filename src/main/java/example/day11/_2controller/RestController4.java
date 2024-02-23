package example.day11._2controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // @Controller + @ResponseBody
@RequestMapping("/day11") // 해당 클래스 내 매핑함수들의 공통 URL
public class RestController4 {
    // 1.
    @GetMapping("/ajax1")
    public String ajax1(){
        System.out.println("RestController4.ajax1");
        return "응답1";
    }

    // 2. 경로상 변수 활용한 매개변수 요청 받기
    @GetMapping("/ajax2/{id}/{content}")
    public String ajax2(@PathVariable int id , @PathVariable String content){
        System.out.println("RestController4.ajax2");
        System.out.println("int id " + id + "String content" + content);
        return "응답2";
    }

    // 3. 쿼리스트링
    // 3-4. Dto 사용
    @GetMapping("/ajax3")
    public String ajax3(AjaxDto ajaxDto){
        System.out.println("RestController4.ajax3");
        System.out.println("ajaxDto = " + ajaxDto);
        return "응답3";
    }
    // 3-1. @RequestParam
//    @GetMapping("/ajax3")
//    public String ajax3(int id , @RequestParam("content") String content2){
//        System.out.println("RestController4.ajax3");
//        System.out.println("int id " + id + "String content" + content2);
//        return "응답";
//    }
    
    // 3-2. HttpServletRequest 객체
//    @GetMapping("/ajax3")
//    public String ajax3(HttpServletRequest req){
//        int id = Integer.parseInt(req.getParameter("id"));
//        String content2 = req.getParameter("content");
//        System.out.println("int id " + id + "String content" + content2);
//        return "응답";
//    }
    // 3-3. @RequestParam Map
//    @GetMapping("/ajax3")
//    public String ajax3(@RequestParam Map<String , String> map){
//        System.out.println("RestController4.ajax3");
//        System.out.println("map = " + map);
//        return "응답";
//    }
    // 3-4. Dto 사용
//    @GetMapping("/ajax3")
//    public String ajax3(AjaxDto ajaxDto){
//        System.out.println("RestController4.ajax3");
//        System.out.println("ajaxDto = " + ajaxDto);
//        return "응답";
//    }

    // 4. HTTP body 본문
//    @GetMapping("/ajax4")
//    public String ajax4(int id , @RequestParam("content") String content){
//        System.out.println("RestController4.ajax4");
//        System.out.println("int id " + id + "String content" + content);
//        return "응답4";
//    }
    @GetMapping("/ajax4")
    public String ajax4(@RequestParam Map<String,String> map){
        System.out.println("RestController4.ajax4");
        System.out.println("map = " + map);
        return "응답4";
    }
}
/*
    ajax
        - 비동기 통신 메소드
        - jquery(js 라이브러리)
        - 사용방법
            1. HTML에서 jquery 라이브러리 호출
            <script src="http://code.jquery.com/jquery-latest.min.js"></script>
            2. js에서 ajax작성
        - 기본문법
            $.ajax();
            $.ajax({});
            $.ajax({utl:"",method:""});
        - ajax 정보 객체 속성
            1. url : "URL",
            2. method : "HTTP METHOD",
            3. success : "HTTP Response"
                1. success : (result) => {console.log(result);}
                2. success : function(result) => {console.log(result);}
                1. success : function 함수명(result) => {console.log(result);}
            4. error : "HTTP ERROR MSG"
            5. data : "HTTP send data"
                method : get,delete --> 쿼리스트링
                method : post,put --> body(본문)
*/