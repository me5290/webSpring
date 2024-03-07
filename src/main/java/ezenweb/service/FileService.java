package ezenweb.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

@Service // 해당 클래스를 스프링 컨테이너(저장소)에 빈(객체) 등록
public class FileService {
    @Autowired
    HttpServletResponse response;
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
    public void fileDownLoad(String bfile){
        System.out.println("FileService.fileDownLoad");
        System.out.println("bfile = " + bfile);
        // 1. 다운로드할 파일의 경로와 파일명 연결해서 해당 파일 찾기
        String downloadPath = uploadPath+bfile;
        System.out.println("downloadPath = " + downloadPath);

        // 2. 해당 파일을 객체로 가져오기 [File클래스는 해당 경로의 파일을 객체로 가져와서 다양한 메소드/기능 제공]
        File file = new File(downloadPath);
        System.out.println("file = " + file);

        // 3. .exists() : 해당 경로에 파일이 있는지 없는지 판별
        if(file.exists()){
            System.out.println("첨부파일 있다.");
            // HTTP가 파일 전송하는 방법 : 파일을 바이트로 전송

            try {
                // HTTP로 응답시 응답방법에 대한 정보를 추가
                    // 기본값은 url 한글 지원 안한다.
                    // url에 한글 지원 하기 위해서는 URLEncoder.encode(url정보,"utf-8");
                response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(bfile.split("_")[1],"utf-8"));

                // 1. 해당파일을 바이트로 불러온다. [ BufferedInputStream ]
                // 1-1. 파일 입력 스트림(바이트가 다니는 통로) 객체 생성
                BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));

                // 1-2. 바이트 배열(고정길이) vs 리스트(가변길이)
                    // 1. 파일의 사이즈/크기/용량 (파일의 크기만큼 바이트배열 선언하기 위해)
                    long fileSize = file.length();
                    // 2. 해당 파일의 사이즈 만큼 바이트 배열 선언
                    byte[] bytes = new byte[(int)fileSize]; // 배열의 길이는 int형

                // 1-3. 입력(불러오기) 메소드
                    // 바이트 하나씩 읽어오면서 바이트배열 저장 => 바이트 배열 필요하다
                fin.read(bytes); // - 익명스트림객체.read(바이트배열) : 하나씩 바이트를 읽어와서 해당 바이트 배열에 저장 해주는 함수

                // 1-4 확인
                System.out.println("bytes = "+ bytes);

                // 2. 불러온 바이트를 HTTP response 이용한 바이트로 응답한다. [ BufferedOutputStream ]
                // 2-1. HTTP 응답스트림 객체 생성
                BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream()) ;
                // 2-2. 내보내기 할 바이트 배열 준비 상태이면 내보내기
                fout.write(bytes);
            } catch (Exception e) {
                System.out.println("e = " + e);
            }
        }else {
            System.out.println("첨부파일 없다.");
        }
    }

    // 3. 파일 삭제[게시물 삭제시 만약에 첨부파일 있으면 첨부파일도 같이 삭제 , 게시물 수정시 첨부파일 변경하면 기존 첨부파일 삭제]
    public boolean fileDelete(String bfile){
        // 1. 경로와 파일을 합쳐서 파일 위치 찾기
        String filePath = uploadPath+bfile;

        // 2. File클래스의 메소드 활용
            // .exists() : 해당 파일의 존재 유무
            // .length() : 해당 파일의 크기/용량(바이트단위)
            // .delete() : 해당 파일을 삭제
        File file = new File(filePath);
        if(file.exists()){ // 만약에 해당 경로에 파일이 존재하면 삭제
            return file.delete(); // 해당 경로에 파일 삭제
        }
        return false;
    }
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
