package com.example.communityboard.controller;

import com.example.communityboard.service.BoardService;
import com.example.communityboard.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class CommunityControl {
    private final BoardService boardService;
    private final CommunityService communityService;

    @GetMapping("article/{id}/update") //title.id 2
    public String update(
            @PathVariable("id") //title.id 2
            Long id,
            Model model
    ){
        //select
        model.addAttribute("boards",boardService.readBoardAll());
        //title.id 2
        model.addAttribute("titles",communityService.readCommunity(id));
        return "article/update";
    }


    @PostMapping("article/{id}/updateView") //title.id= 3홍길동
    public String updateView(
            @PathVariable("id")
            Long id, //3
            @RequestParam("boardID")
            Long boardID,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            Integer password
            ) {
        communityService.update(
                id, boardID, title, content, password);
            return String.format("redirect:/boards/%d", boardID);
        }

    @PostMapping("article/{id}/delete") //title.id
    public String delete(@PathVariable("id") Long id){
        //'redirect:/boards/삭제했던 페이지'로 가기위한 서비스로직
        Long boardID = communityService.boardID(id);
        communityService.delete(id);
        return String.format("redirect:/boards/%d", boardID); //article/{id}로 보내고 싶은데 실패.
    }

}
