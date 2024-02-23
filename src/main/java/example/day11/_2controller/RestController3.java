package example.day11._2controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/day11")
public class RestController3 { // HttpServletResponse 없애기
    // 1. Get
    // @RequestMapping(value = "/day11/red" , method = RequestMethod.GET)
    @GetMapping("/red")
    public String getRed(String sendMsg) throws IOException{
        // 요청
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return "안녕클라이언트";
    }

    // 2. Post
    @PostMapping("/red")
    public Map<String, String> postRed(HttpServletRequest req) throws IOException{
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        Map<String,String> strArray = new HashMap<>();
        strArray.put("안녕","클라이언트");
        return strArray;
    }

    // 3. Put
    @PutMapping("/red")
    public int putRed(HttpServletRequest req) throws IOException{
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return 10;
    }

    // 4. Delete
    @DeleteMapping("/red")
    public boolean deleteRed(HttpServletRequest req) throws IOException{
        // 요청
        String sendMsg = req.getParameter("sendMsg");
        System.out.println("sendMsg = " + sendMsg);
        // 응답
        return true;
    }
}
