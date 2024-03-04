package ezenweb.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
}

/*
    글쓰기용
        - 입력받기 : btitle , bcontent , bfile , bcno
        - 서버처리 :
*/
