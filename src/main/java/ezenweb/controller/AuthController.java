package ezenweb.controller;

import ezenweb.service.EmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private EmailService emailService;

    // 1. email 인증 요청
    @GetMapping("/email/request")
    public boolean getEmailEeq(@RequestParam String email){
        System.out.println("AuthController.getEmailEeq");
        // 1. 난수 이용한 6자리 인증코드 발급
        Random random = new Random();
        String ecode = "";
        for (int i=0; i<6; i++) {
            ecode += random.nextInt(10); // 10 : 0~9 - .nextInt(미만)+시작번호
        }
        System.out.println("ecode = "+ecode);

        // 2. HTTP세션에 특정시간만큼 발급된 인증코드 보관
            // 1. 세션에 속성 추가해서 발급된 인증코드 대입하기
        request.getSession().setAttribute("ecode",ecode);
            // 2. 세션에 생명주기 넣어주기
        request.getSession().setMaxInactiveInterval(120);

        // 3. 발급된 인증코드를 클라이언트의 인증할 이메일로 전송
        emailService.send(email,"리민형 인증코드","인증코드는 "+ ecode+" 입니다.");

        return true;
    }

    // 2. email 인증 확인
    @GetMapping("/email/check")
    public boolean getEmailCheck(@RequestParam String certi){
        System.out.println("AuthController.getEmailCheck");
        System.out.println("certi = " + certi);

        // 1. HTTP세션에 보관했던 인증코드를 꺼낸다
            // 1. 세션 속성 호출
        Object object = request.getSession().getAttribute("ecode");
            // 2. 만약에 세션 속성이 존재하면
        if(object != null){
            String ecode = (String)object; // 강제타입변환
            // 3. 발급된 인증코드와 입력받은 인증코드와 같으면
            if(ecode.equals(certi)){
                return true;
            }
        }
        return false;
    }
}
