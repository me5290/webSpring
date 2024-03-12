package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.BoardPageDto;
import ezenweb.service.BoardService;
import ezenweb.service.FileService;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FileService fileService;

    // 1. 글쓰기 처리
    @PostMapping("/write.do")
    @ResponseBody
    public long doPostBoardWrite(BoardDto boardDto){
        System.out.println("BoardController.doPostBoardWrite");

        // 1. 현재 로그인된 세션(톰캣서버 메모리(JVM) 저장소) 호출
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null){
            return -2;
        }

        // 2. 형변환
        String mid = (String) object;

        // 3. mid를 mno 찾아오기
        long mno = memberService.doGetLoginInfo(mid).getNo();

        // 4.
        boardDto.setMno(mno);

        return boardService.doPostBoardWrite(boardDto);
    }

    // 2. 전체 글 출력 호출
    @GetMapping("/do") // (쿼리스트링)매개변수 : 현재페이지
    @ResponseBody
    public BoardPageDto doGetBoardViewList(@RequestParam int page , @RequestParam int pageBoardSize , @RequestParam int bcno , @RequestParam String key , @RequestParam String keyword){
        System.out.println("BoardController.doGetBoardViewList");
        return boardService.doGetBoardViewList(page , pageBoardSize , bcno , key , keyword);
    }

    // 3. 개별 글 출력 호출
    @GetMapping("/view.do")
    @ResponseBody
    public BoardDto doGetBoardView(@RequestParam int bno){
        System.out.println("BoardController.doGetBoardView");
        return boardService.doGetBoardView(bno);
    }

    // 4. 글 수정 처리
    @PutMapping("/update.do")
    @ResponseBody
    public boolean doUpdateBoard(BoardDto boardDto){
        System.out.println("BoardController.doPutBoard");
        // 유효성검사
            // 1. 현재 로그인된 아이디(세션)
            Object object = request.getSession().getAttribute("loginDto");
            if(object != null){
                String mid = (String) object;
                boolean result = boardService.boardWriterAuth(boardDto.getBno(),mid); // 해당 세션정보가 작성한 글인지 체크
                if (result){
                    // 2. 현재 수정할 게시물의 작성자 아이디(DB)
                    return boardService.doUpdateBoard(boardDto);
                }
            }
        return false;
    }

    // 5. 글 삭제 처리
    @DeleteMapping("/delete.do")
    @ResponseBody
    public boolean doDeleteBoard(@RequestParam int bno){
        System.out.println("BoardController.doDeleteBoard");
        // 유효성검사
            // 1. 현재 로그인된 아이디(세션)
            Object object = request.getSession().getAttribute("loginDto");
            if(object != null){
                String mid = (String) object;
                boolean result = boardService.boardWriterAuth(bno,mid); // 해당 세션정보가 작성한 글인지 체크
                if (result){
                    // 2. 현재 수정할 게시물의 작성자 아이디(DB)
                    return boardService.doDeleteBoard(bno);
                }
            }
        return false;
    }

    // 6. 다운로드 처리 (1.매개변수 : 파일명 , 2.반환 : , 3.사용처 : get http요청)
    @GetMapping("/file/download")
    @ResponseBody
    public void getBoardFileDownload(@RequestParam String bfile){
        System.out.println("BoardController.getBoardFileDownload");
        System.out.println("bfile = " + bfile);
        fileService.fileDownLoad(bfile);
        return;
    }

    // 7. 댓글 작성 (brcontent , brindex , mno , bno)
    @PostMapping("/reply/write.do")
    @ResponseBody
    public boolean doPostReplyWrite(@RequestParam Map<String,String > map){
        System.out.println("BoardController.doPostReplyWrite");
        System.out.println("map = " + map);

        // 1. 현재 로그인된 세션(톰캣서버 메모리(JVM) 저장소) 호출
        Object object = request.getSession().getAttribute("loginDto");
        if(object == null){
            return false;
        }

        // 2. 형변환
        String mid = (String) object;

        // 3. mid를 mno 찾아오기
        long mno = memberService.doGetLoginInfo(mid).getNo();

        // 4. map에 mno 넣기
        map.put("mno",mno+"");

        return boardService.doPostReplyWrite(map);
    }

    // 8. 댓글 출력 (brno , brcontent , brdate , brindex , mno)
    @GetMapping("/reply/do")
    @ResponseBody
    public List<Map<String ,Object >> doGetReplyDo(int bno){
        System.out.println("BoardController.doGetReplyDo");
        return boardService.doGetReplyDo(bno);
    }

    // =============================== //

    // 1. 글쓰기 페이지 이동
    @GetMapping("/write")
    public String getBoardWrite(){
        return "ezen/board/write";
    }

    // 2. 게시판 페이지 이동
    @GetMapping("")
    public String getBoard(){
        return "ezen/board/board";
    }

    // 3. 게시판 상세 페이지 이동
    @GetMapping("/view")
    public String getBoardView(int bno){
        return "ezen/board/view";
    }

    // 4. 글 수정 페이지 이동
    @GetMapping("/update")
    public String getBoardUpdate(){
        return "ezen/board/update";
    }
}
