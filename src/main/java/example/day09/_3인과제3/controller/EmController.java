package example.day09._3인과제3.controller;

import example.day09._3인과제3.model.dao.EmDao;
import example.day09._3인과제3.model.dto.EmDto;
import example.day09._3인과제3.model.dto.PlusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmController {
    @Autowired
    EmDao emDao;

    // 1. 페이지 출력
    @GetMapping("/team3/index")
    public String view(){
        System.out.println("EmController.view");
        return "/static/team3/index.html";
    }

    // 2. 사원등록
    @PostMapping("/team3/create")
    @ResponseBody
    public boolean create(EmDto emDto){
        System.out.println("EmController.create");
        boolean result = emDao.create(emDto);
        System.out.println("create"+result);
        return result;
    }

    // 3. 사원목록 출력
    @PostMapping("/team3/read")
    @ResponseBody
    public List<EmDto> read(){
        System.out.println("EmController.read");
        List<EmDto> result = emDao.read();
        System.out.println("read"+result);
        return result;
    }

    // 4. 사원 전화번호 수정
    @PostMapping("/team3/update/{no}")
    @ResponseBody
    public boolean update(@PathVariable int no , EmDto emDto){
        System.out.println("EmController.update");
        boolean result = emDao.update(emDto);
        System.out.println("update"+result);
        return result;
    }

    // 5. 사원삭제
    @PostMapping("/team3/delete/{no}")
    @ResponseBody
    public boolean delete(@PathVariable int no){
        System.out.println("EmController.delete");
        boolean result = emDao.delete(no);
        System.out.println("delete"+result);
        return result;
    }

    // ============================================== //

    // 6. 교과 등록
    @PostMapping("/team3/plusCreate")
    @ResponseBody
    public boolean plusCreate(PlusDto plusDto){
        System.out.println("plusDto = " + plusDto);
        boolean result = emDao.plusCreate(plusDto);
        System.out.println("plusCreate"+result);
        return result;
    }

    // 7. 교과 출력
    @PostMapping("/team3/plusRead/{no}")
    @ResponseBody
    public List<PlusDto> plusRead(@PathVariable int no){
        List<PlusDto> result = emDao.plusRead(no);
        System.out.println("plusRead"+result);
        return result;
    }
}
