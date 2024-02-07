package book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 이 클래스가 컨트롤러임을 선언
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){   // 인수에 model 매개변수 선언
        // 머스테치에 전달할 매개변수 등록
        model.addAttribute("username","홍박사");

        // return "머스테치파일명";
        // 서버가 알아서 templates 폴더에 파일을 찾아 브라우저에게 전송
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","홍박사");
        return "goodbye";
    }
}
