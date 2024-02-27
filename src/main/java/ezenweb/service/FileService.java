package ezenweb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service // 해당 클래스를 스프링 컨테이너(저장소)에 빈(객체) 등록
public class FileService {
    // Service : Controller <-- Service(비즈니스로직) --> Dao

    // 어디에(PATH) 누구를(파일객체 MultipartFile)
    String uploadPath = "C:\\Users\\504\\Desktop\\webSpring\\build\\resources\\main\\static\\img\\";

    // 1. 업로드 메소드
    public String fileUpload(MultipartFile multipartFile){
        // 파일 이름 조합하기 : 새로운 식별이름과 실제 파일이름
        String uuid = UUID.randomUUID().toString();
        String filename = uuid+"_"+multipartFile.getOriginalFilename().replaceAll("_","-");

        // 1. [어디에] 첨부파일을 저장할 경로 세팅
        // File 클래스 : 파일 관련된 메소드 제공
        File file = new File(uploadPath+filename);

        // 2. [무엇을] 첨부파일 객체
        // .transferTo(경로)
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            System.out.println("e = " + e);
            return null;
        }
        return filename;
    }

    // 2. 다운로드 메소드
}
/*
     // 서버에 업로드 했을때 설계
            // 1. 여러 클라이언트[다수]가 동일한 파일명으로 서버[1명]에게 업로드 했을때
                // 식별이름 : 1.날짜조합+데이터 , 2.UUID(중복이 거의 없는 식별 난수)
            // 2. 클라이언트 화면 표시
                // 업로드 경로 : 아파치 톰캣(static)에 업로드
        // 업로드 할 경로 설정(내장 톰캣 경로)

     // 1. 첨부파일 업로드 하기 [업로드 : 클라이언트의 바이트(대용량/파일)를 서버에 복사]

     // 식별키와 실제 이름 구분 : 나중에 쪼개서 구분하기 위해서 [다운로드시 식별키 빼고 제공]
        // 혹시나 파일 이름에 구분문자가 있을경우 기준이 깨짐

     // 첨부파일 MultipartFile 타입
        System.out.println(memberDto.getImg());                         // 첨부파일이 들어있는 객체 주소
        System.out.println(memberDto.getImg().getSize());               // 첨부파일 용량 : 36131
        System.out.println(memberDto.getImg().getContentType());        // 첨부파일의 확장자 : image/png
        System.out.println(memberDto.getImg().getOriginalFilename());   // 첨부파일의 이름
        System.out.println(memberDto.getImg().getName());               // form input의 name
*/
