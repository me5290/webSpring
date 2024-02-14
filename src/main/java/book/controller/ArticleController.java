package book.controller;

import book.dao.ArticleDao;
import book.dto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
// 1. 스프링 컨테이너(메모리 저장소)에 빈(객체/힙) 등록
// 2. 스프링이 해당 클래스를 사용할수 있다
// 3. 모든 클라이언트 요청은 컨트롤러로 들어온다
public class ArticleController {
    @Autowired // 스프링 컨테이너에 등록된 빈 주입한다
    ArticleDao articleDao;

    // 1. 글쓰기 페이지 반환
    @GetMapping("/article/new") // HTTP 요청 경로 : GET방식
    public String newArticleForm(){
        return "article/new";   // .확장자 빼고 resource/templates 부터 경로 작성
    }

    // 2. 글쓰기 처리
        // 1. <form action="/article/create" method="post">
        // 2. 입력태그 속성의 name과 DTO 필드의 필드명이 일치하면 자동으로 연결된다
        // 3. public 생성자 필요
    @PostMapping("/article/create")
    public boolean createArticle(ArticleForm form){
        System.out.println("ArticleController.createArticle");
        System.out.println("form = " + form);

        // 개발용(디버그) 로그
        log.debug(form.toString());

        // 경고 로그
        log.warn(form.toString());

        // 에러 로그
        log.error(form.toString());

        // 운영(배포) 로그
        log.info(form.toString());

        // DAO에게 응답받기
        boolean result = articleDao.createArticle(form);

        return result;
    }

    // 156P 조회
        // [개별조회]
        // 1. 클라이언트 서버(spring) 요청 시 id/식별키/pk 전달
        // 2. HTTP URL 이용한 요청 : /articles/1 , /articles/2 , /articles/3
            // 정해진 값이 아닌 매개변수일 경우에는 : /articles/{매개변수명}/{매개변수명}/{매개변수명}
            // 요청 : /articles/1또는2또는3
        // 3. 서버(spring) Controller 요청 URL 매핑/연결 받기
        // 4. @GetMapping("/articles/{매개변수}")
        // 5. 함수 매개변수에서 URL상의 매개변수와 이름 일치
        // 6. 함수 매개변수 앞에 @PathVariable 어노테이션 주입
            // @PathVariable : URL 요청으로 들어온 전달값을 컨트롤러 함수의 매개변수로 가져오는 어노테이션
    @GetMapping("/article/{id}")
    public String show(@PathVariable Long id , Model model){
        System.out.println("id = " + id);
        // 1. 159P : 요청된 id를 가지고 DAO에게 데이터 요청
        ArticleForm form = articleDao.show(id);
        System.out.println("form = " + form);
        // 2. 160P : DAO에게 전달받은 값을 뷰템플릿에게 전달하기 model.addAttribute("키","값");
        model.addAttribute("article",form);
        model.addAttribute("name","유재석");
            // {{변수명}}
            // {{>파일경로}}
        // 3. 161P : 해당 함수가 종료될때 리턴값 1.화면/뷰 (머스테치,HTMl) 2. 값(JSON)
        return "article/show";
    }

    // 170P 조회
        // [전체조회]
    @GetMapping("/article")
    public String index(Model model){
        // 1. DAO에게 요청해서 데이터 가져온다
        List<ArticleForm> result = articleDao.index();
        // 2. 뷰템플릿(머스테치)에게 전달할 값을 model에 담아준다
        model.addAttribute("articleList",result);
        // 3. 뷰 페이지 설정
        return "article/index";
    }
}
/*
    @ 어노테이션
        1. 표준 어노테이션 : 자바에서 기본적으로 제공
            @Override : 메소드 재정의 등등
        2. 메타 어노테이션 : 64P
            - 소스코드에 추가해서 사용하는 메타 데이터
            - 메타 데이터 : 구조화된 데이터
            - 컴파일 또는 실행 했을때 코드를 어떻게 처리 해야 할지 알려주는 정보
            @SpringBootApplication
                1. 내장 서버(톰캣) 지원
                2. 스프링 MVC 내장
                    View : resource

                    Controller

                    Model
            @Controller
            @GetMapping
*/