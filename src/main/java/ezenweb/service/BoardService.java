package ezenweb.service;

import ezenweb.model.dao.BoardDao;
import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.BoardPageDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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
    public BoardPageDto doGetBoardViewList(int page){
        System.out.println("BoardService.doGetBoardViewList");

        // 페이징처리시 사용할 SQL구문 : limit 시작레코드번호(0부터) , 개수

        // 1. 페이지당 게시물을 출력할 개수
        int pageBoardSize = 1;

        // 2. 페이지당 게시물을 출력할 시작 레코드번호
        int startRow = (page-1)*pageBoardSize;

        // 3. 총 페이지수
            // 1. 전체 게시물수
        int totalBoardSize = boardDao.getBoardSize();
            // 2. 총 페이지수 계산
        int totalPage = totalBoardSize%pageBoardSize == 0 ? totalBoardSize/pageBoardSize : totalBoardSize/pageBoardSize+1;

        // 4. 게시물 정보 요청
        List<BoardDto> list = boardDao.doGetBoardViewList(startRow,pageBoardSize);

        // 5. 페이징 버튼 개수
            // 1. 페이지버튼 최대 개수
        int btnSize = 5;
            // 2. 페이지버튼 시작번호
        int startBtn = ((page-1)/btnSize)*btnSize+1;
            // 3. 페이지버튼 끝번호
        int endBtn = startBtn+4;
            // 만약에 페이지버튼의 끝번호가 총 페이지수 보다는 커질수 없다
        if(endBtn >= totalPage){
            endBtn = totalPage;
        }

        // pageDto 구성
        BoardPageDto boardPageDto = new BoardPageDto(page,totalPage,startBtn,endBtn,list);

        return boardPageDto;
    }

    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(int bno){
        System.out.println("BoardController.doGetBoardView");
        return boardDao.doGetBoardView(bno);
    }

    // 4. 글 수정 처리

    // 5. 글 삭제 처리
}
