package ezenweb.service;

import ezenweb.model.dao.BoardDao;
import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.BoardPageDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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
    public BoardPageDto doGetBoardViewList(int page , int pageBoardSize , int bcno , String key , String keyword){
        System.out.println("BoardService.doGetBoardViewList");

        // 페이징처리시 사용할 SQL구문 : limit 시작레코드번호(0부터) , 개수

        // 1. 페이지당 게시물을 출력할 개수
        // int pageBoardSize = 1;

        // 2. 페이지당 게시물을 출력할 시작 레코드번호
        int startRow = (page-1)*pageBoardSize;

        // 3. 총 페이지수
            // 1. 전체 게시물수
        int totalBoardSize = boardDao.getBoardSize(bcno , key , keyword);
            // 2. 총 페이지수 계산
        int totalPage = totalBoardSize%pageBoardSize == 0 ? totalBoardSize/pageBoardSize : totalBoardSize/pageBoardSize+1;

        // 4. 게시물 정보 요청
        List<BoardDto> list = boardDao.doGetBoardViewList(startRow,pageBoardSize,bcno,key,keyword);

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

        // pageDto 구성 - 빌더패턴 : 생성자의 단점 (매개변수에 따른 유연성 부족)을 보완
        // BoardPageDto boardPageDto = new BoardPageDto(page,totalPage,startBtn,endBtn,list);
            // new 연산자 없이 builder()함수를 이용한 객체 생성 라이브러리 제공
            // 사용방법 : 클래스명.builder().필드명(대입값).필드명(대입값).build();
            // 생성자 보단 유연성 있다 , 매개변수의 순서와 개수가 자유롭다.
        BoardPageDto boardPageDto = BoardPageDto.builder().page(page).totalBoardSize(totalBoardSize).totalPage(totalPage).list(list).startBtn(startBtn).endBtn(endBtn).build();

        return boardPageDto;
    }

    // 3. 개별 글 출력 호출
    public BoardDto doGetBoardView(int bno){
        System.out.println("BoardController.doGetBoardView");
        boardDao.doPostBview(bno);
        return boardDao.doGetBoardView(bno);
    }

    // 4. 글 수정 처리
    public boolean doUpdateBoard(BoardDto boardDto){
        System.out.println("BoardService.doPutBoard");
        return boardDao.doUpdateBoard(boardDto);
    }

    // 5. 글 삭제 처리
    public boolean doDeleteBoard( int bno){
        System.out.println("BoardService.doDeleteBoard");
        // 게시판 정보 호출
        String bfile = boardDao.doGetBoardView(bno).getBfile();

        // 1. DB 처리
        boolean result = boardDao.doDeleteBoard(bno);

        // 2. DB처리 성공시 첨부파일도 삭제
        if(result){
            // 기존에 첨부파일이 있었으면
            if(bfile != null){
                fileService.fileDelete(bfile);
            }
        }
        return result;
    }
}
