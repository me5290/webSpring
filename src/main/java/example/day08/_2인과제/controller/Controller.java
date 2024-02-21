package example.day08._2인과제.controller;

import example.day08._2인과제.dao.Dao;
import example.day08._2인과제.dto.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    Dao dao;
    // 리스트 출력
    @GetMapping("/todolist")
    public String listShow(){
        return "todolist";
    }
    @GetMapping("/todolist/read")
    @ResponseBody
    public List<Dto> readList(){
        List<Dto> dtos = dao.viewList();
        System.out.println(dtos);
        return dtos;
    }

    // 리스트 추가 요청 처리
    @PostMapping("/todolist/create")
    @ResponseBody
    public boolean createList(Dto dto){
        boolean result = dao.createList(dto);
        System.out.println("추가"+result);
        return result;
    }

    // 리스트 상태 변경
    @PostMapping("/todolist/state/{no}")
    @ResponseBody
    public boolean stateList(@PathVariable int no){
        boolean result = dao.stateList(no);
        System.out.println("상태변경"+result);
        return result;
    }

    // 리스트 수정 요청 처리
    @PostMapping("/todolist/update/{no}")
    @ResponseBody
    public boolean updateList(@PathVariable int no , Dto dto){
        boolean result = dao.updateList(dto);
        System.out.println("수정"+result);
        return result;
    }

    // 리스트 삭제 요청 처리
    @PostMapping("/todolist/delete/{no}")
    @ResponseBody
    public boolean deleteList(@PathVariable int no){
        System.out.println(no);
        boolean result = dao.deleteList(no);
        System.out.println("삭제"+result);
        return result;
    }
}
