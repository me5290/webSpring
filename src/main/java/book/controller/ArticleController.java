package book.controller;

import book.dao.ArticleDao;
import book.dto.ArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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