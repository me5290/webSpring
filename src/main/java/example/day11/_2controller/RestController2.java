package example.day11._2controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RestController2 { // HttpServletResponse 없애기
    // 1. Get
    @RequestMapping(value = "/day11/white" , method = RequestMethod.GET)
    @ResponseBody
    public String getWhite(HttpServletRequest req ) throws IOException{
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return "안녕클라이언트";
    }

    // 2. Post
    @RequestMapping(value = "/day11/white" , method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> postWhite(HttpServletRequest req) throws IOException{
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
//        String[] strArray = new String[2];
//        strArray[0]="어어 그래";
//        strArray[1]="클라이언트";
//        List<String> strArray = new ArrayList<>();
//        strArray.add("안녕");
//        strArray.add("클라이언트");
        Map<String,String> strArray = new HashMap<>();
        strArray.put("안녕","클라이언트");
        return strArray;
    }

    // 3. Put
    @RequestMapping(value = "/day11/white" , method = RequestMethod.PUT)
    @ResponseBody
    public int putWhite(HttpServletRequest req) throws IOException{
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return 10;
    }

    // 4. Delete
    @RequestMapping(value = "/day11/white" , method = RequestMethod.DELETE)
    @ResponseBody
    public boolean deleteWhite(HttpServletRequest req) throws IOException{
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return true;
    }
}
