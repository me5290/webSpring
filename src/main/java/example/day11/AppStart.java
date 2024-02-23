package example.day11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}
/*
    @ServletComponentScan
        - 스프링MVC 환경에서 서블릿을 단독적으로 사용할때

    @SpringBootApplication
        - 스프링에서 웹에 관련된 기능을 주입
        - 1. Uses Apache Tomcat as the default embedded container 내장 아파치톰캣 설정/실행
            ip : 톰캣이 설치된 컴퓨터의 ip주소
            port : 톰캣(소프트웨어) 식별번호
                프로젝트내 src -> main -> resources -> application.properties
                    server.port = 5290
                * application.properties : 스프링 설정파일 (MAP 컬렉션프레임워크 타입)

        - 2. including RESTful(Get , Post , Put , Delete)

        - 3. applications using Spring MVC(model , view , controller)
            - 컴포넌트 스캔해서 빈(객체)를 스프링컨테이너에 등록 (상위 패키지의 클래스는 스캔 불가)
                - @Controller
                - @RestController
                - @Component
                - 등등

        - 스프링 실행 방법
            SpringApplication.run(현재클래스.class);
*/