package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder // 생성자의 단점을 보완한 라이브러리 함수 제공
public class BoardDto {
    long bno;
    String btitle;
    String bcontent;
    String bfile;
    long bview;
    String bdate;
    long mno;
    long bcno;
    MultipartFile uploadfile;
    // 전체출력시 필드
    String mid;
    String mimg;
}

/*
    - 용도에 따라 다양한 DTO가 존재할수 있다
    - 하나의 DTO에 서로다른 용도로 사용

    1. 글쓰기용
        - 입력받기 : btitle , bcontent , bfile , bcno
        - 서버처리 : bno자동 , bview기본값0 , bdate기본값현재날짜 mno로그인(세션)

    2. 개별 출력용
        - 출력용 : bno , btitle , bcontent , bfile , bview , bdate , mno , bcno

    3. 전체 출력용
        - 출력용 : bno , btitle , bcontent , bfile , bview , bdate , mno , bcno , mid , mimg

    - 생성자 단점/규칙 : 매개변수의 순서 , 개수 => 유연성 떨어짐
    - 빌더 패턴 : @Builder
*/
