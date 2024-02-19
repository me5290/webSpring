package ezenweb.controller;

import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    // 1단계. HTTP 통신 방식 ( V <----> C )
    // 2단계. Controller mapping 함수 선언하고 통신 체크 (API Tester)
    // 3단계. Controller request 매개변수 매핑
    // 4단계. 응답
    //          1. 뷰 반환 : text/html;  vs   2. 데이터 : @ResponseBody - Application/JSON

    // 1. 회원가입 처리 요청
    @PostMapping("member/signup")
    @ResponseBody   // 응답방식 : JSON
    public Boolean signup(MemberDto memberDto){
        System.out.println("MemberController.signup");
        System.out.println("memberDto = " + memberDto);
        boolean result = true;  // Dao 처리
        return result;
    }

    // 2. 로그인 처리 요청
    @PostMapping("/member/login")
    @ResponseBody   // 응답방식 : JSON
    public Boolean login(LoginDto liginDto){
        System.out.println("MemberController.login");
        System.out.println("liginDto = " + liginDto);
        boolean result = true;  // Dao 처리
        return result;
    }

    // 3. 회원가입 페이지 요청
    @GetMapping("member/signup")
    public String viewSignup(){
        System.out.println("MemberController.viewSignup");
        return "ezen/signup";
    }

    // 4. 로그인 페이지 요청
    @GetMapping("/member/login")
    public String viewLogin(){
        System.out.println("MemberController.viewLogin");
        return "ezen/login";
    }

    // 5. 회원수정 처리 요청
    @PostMapping("/member/{id}/update")
    @ResponseBody
    public Boolean update(@PathVariable int id){
        System.out.println("MemberController.update");
        boolean result = true;
        return result;
    }

    // 6. 회원수정 페이지 요청
    @GetMapping("/member/{id}/update")
    public String viewupdate(@PathVariable int id){
        System.out.println("MemberController.viewupdate");
        return "ezen/update";
    }

    // 7. 회원탈퇴 요청
    @GetMapping("/member/{id}/delete")
    @ResponseBody
    public Boolean delete(@PathVariable int id){
        System.out.println("MemberController.delete");
        boolean result = true;
        return result;
    }
}
