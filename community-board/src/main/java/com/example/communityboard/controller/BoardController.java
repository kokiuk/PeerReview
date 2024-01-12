package com.example.communityboard.controller;

import com.example.communityboard.entity.Board;
import com.example.communityboard.service.BoardService;
import com.example.communityboard.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final CommunityService communityService;

    @GetMapping
    public String boards(Model model){
        model.addAttribute("boards", boardService.readBoardAll());
        return "board/boards";
    }

    @GetMapping("/{id}") //board_id =3
    public String readOne(
            @PathVariable("id")
            Long id,
            Model model
    ){
        //article 목차
        model.addAttribute("board", boardService.readBoard(id));
        model.addAttribute("titles", communityService.readCommunityAll());

        return "board/read";
    }

    @GetMapping("/{id}/article")
    public String article(
//        @PathVariable("id")
//        Long id,
        Model model
    ){
        //select
        model.addAttribute("boards", boardService.readBoardAll());
        return "board/article";
    }

    @GetMapping("/{id}/comment")
    public String comment(

    ){
        return "board/comment";
    }

    @PostMapping("/create")
    public String create(
        @RequestParam("boardID")
        Long boardID, //board_id = 4
        @RequestParam("title")
        String title,
        @RequestParam("content")
        String content,
        @RequestParam("password")
        Integer password
    ){
        communityService.create(boardID, title, content, password);

        return String.format("redirect:/boards/%d", boardID);
    }




}
