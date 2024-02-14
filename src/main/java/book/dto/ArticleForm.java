package book.dto;

import lombok.*;

// 입력 폼 전용 DTO
    // 관례적으로 모든 객체 필드는 직접 접근 권장하지 않는다. private , 안정성보장 , 캡슐화 특징
    // 필드는 private , 생성자는 빈/풀 , 메소드는 tostring/getter/setter
@AllArgsConstructor // 컴파일시 모든 필드 생성자를 자동으로 만들어준다
@NoArgsConstructor // 컴파일시 기본 생성자를 자동으로 만들어준다
@ToString // 컴파일시 toString() 자동으로 만들어준다
@Getter // 컴파일시 getter() 자동으로 만들어준다
@Setter // 컴파일시 setter() 자동으로 만들어준다
public class ArticleForm {
    // 1. 필드
    private Long id;
    private String title;
    private String content;

    // 2. 생성자

    // 3. 메소드

}
