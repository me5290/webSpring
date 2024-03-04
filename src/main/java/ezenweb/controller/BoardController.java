package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.service.BoardService;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MemberService memberService;

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

    // 3. 개별 글 출력 호출
    @GetMapping("/view.do")
    @ResponseBody
    public BoardDto doGetBoardView(@RequestParam int bno){
        System.out.println("BoardController.doGetBoardView");
        return boardService.doGetBoardView(bno);
    }

    // 4. 글 수정 처리

    // 5. 글 삭제 처리

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
}
