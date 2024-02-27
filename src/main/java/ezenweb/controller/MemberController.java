package ezenweb.controller;

import ezenweb.model.dao.MemberDao;
import ezenweb.model.dto.LoginDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.service.FileService;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MemberController {
    // 1단계. HTTP 통신 방식 ( V <----> C )
    // 2단계. Controller mapping 함수 선언하고 통신 체크 (API Tester)
    // 3단계. Controller request 매개변수 매핑
    // 4단계. 응답
    //          1. 뷰 반환 : text/html;  vs   2. 데이터 : @ResponseBody - Application/JSON

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberService memberService;

    // 1. 회원가입 처리 요청
    @PostMapping("/member/signup")
    @ResponseBody   // 응답방식 : JSON
    public Boolean signup(MemberDto memberDto){
        return memberService.doPostSignup(memberDto);
    }

    // 2. 로그인 처리 요청
    @PostMapping("/member/login")
    @ResponseBody   // 응답방식 : JSON
    public Boolean login(LoginDto loginDto){
        boolean result = memberDao.doPostLogin(loginDto);  // Dao 처리
        // 로그인 성공시
            // 세션 저장소 : 톰캣서버에 브라우저 마다의 메모리 할당
            // 세션 객테 타입 : Object(여러가지 타입들을 저장하려고)
                // 1. Http요청 객체 호출 HttpServletRequest
                // 2. Http세션 객체 호출 .getSession()
                // 3. Http세션 데이터 저장 .setAttribute("세션명",값)
                // -  Http세션 데이터 호출 .getAttribute("세션명")
                // -  Http세션 데이터 초기화 .invalidate()
        if(result){ // 만약에 로그인 성공이면 세션 부여
            // 세션에 저장할 내용물들을 구성(식별키 만)
            request.getSession().setAttribute("loginDto",loginDto.getId());
        }
        return result;
    }
    // 2-2. 로그인 여부 확인 요청
    @GetMapping("/member/login/check")
    @ResponseBody
    public String doGetLoginCheck(){
        // 로그인 여부 확인 = 세션이 있다 없다 확인
            // 1. Http요청 객체 호출
            // 2. Http세션 객체 호출
            // 3. Http세션 데이터 호출
        // null 형변환이 불가능하기 때문에 유효성검사
        String loginDto = null;
        Object sessionObj = request.getSession().getAttribute("loginDto");
        if (sessionObj != null){
            loginDto = (String)sessionObj;
        }
        // 만약에 로그인 했으면(세션에 데이터가 있으면) 강제형변환을 통해 데이터 호출 아니면 0
        return loginDto;
    }
    // 2-3. 로그아웃/세션초기화
    @GetMapping("/member/logout")
    @ResponseBody
    public boolean logout(){
        // 1. 모든 세션 초기화 => 로그인세션 외 다른 세션도 고려
        request.getSession().invalidate(); // 현재 요청 보낸 브라우저의 모든 세션 초기화
        // 2. 특정 세션 초기화 => 동일한 세션속성명에 null 대입
        // request.getSession().setAttribute("loginDto",null);
        return true;
    }

    // 3. 회원정보 요청 (로그인된 회원 요청 , 패스워드 제외)
    @GetMapping("/member/login/info")
    @ResponseBody
    public MemberDto doGetLoginInfo(String id){
        return memberService.doGetLoginInfo(id); // 서비스 요청과 응답 전달
    }

    // 4. 회원가입 페이지 요청
    @GetMapping("/member/signup")
    public String viewSignup(){
        System.out.println("MemberController.viewSignup");
        return "ezen/signup";
    }

    // 5. 로그인 페이지 요청
    @GetMapping("/member/login")
    public String viewLogin(){
        System.out.println("MemberController.viewLogin");
        return "ezen/login";
    }

    // 6. 회원수정 처리 요청
    @PostMapping("/member/{id}/update")
    @ResponseBody
    public Boolean update(@PathVariable int id){
        System.out.println("MemberController.update");
        boolean result = true;
        return result;
    }

    // 7. 회원수정 페이지 요청
    @GetMapping("/member/{id}/update")
    public String viewupdate(@PathVariable int id){
        System.out.println("MemberController.viewupdate");
        return "ezen/update";
    }

    // 8. 회원탈퇴 요청
    @GetMapping("/member/{id}/delete")
    @ResponseBody
    public Boolean delete(@PathVariable int id){
        System.out.println("MemberController.delete");
        boolean result = true;
        return result;
    }

    @GetMapping("/")
    public String viewMain(){
        return "ezen/index";
    }
}
