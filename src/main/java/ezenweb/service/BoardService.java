package ezenweb.service;

import ezenweb.model.dao.BoardDao;
import ezenweb.model.dto.BoardDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class BoardService {
    @Autowired
    private BoardDao boardDao;
    @Autowired
    private FileService fileService;

    // 1. 글쓰기 처리
    public long doPostBoardWrite(BoardDto boardDto){
        System.out.println("BoardController.doPostBoardWrite");

        // 1. 첨부 파일 처리
            // 첨부 파일이 존재하면
        if(!boardDto.getUploadfile().isEmpty()){
            String fileName = fileService.fileUpload(boardDto.getUploadfile());
            if(fileName != null){ // 업로드 성공했으면
                boardDto.setBfile(fileName); // DB에 저장할 첨부파일명
            }else {
                return -1;
            }
        }

        // 2. DB 처리
        return boardDao.doPostBoardWrite(boardDto);
    }

    // 2. 전체 글 출력 호출

    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(int bno){
        System.out.println("BoardController.doGetBoardView");
        return boardDao.doGetBoardView(bno);
    }

    // 4. 글 수정 처리

    // 5. 글 삭제 처리
}
