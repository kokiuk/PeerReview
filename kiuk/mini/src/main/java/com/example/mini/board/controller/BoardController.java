package com.example.mini.board.controller;

import com.example.mini.board.service.BoardService;
import com.example.mini.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final PostService postService;

    @GetMapping("home")
    public String home(Model model){
        model.addAttribute("boards", boardService.readBoardAll());
        return "home";
    }

    @GetMapping("{id}")
    public String boardDetail(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("board", boardService.readBoardOne(id));
        model.addAttribute("posts", boardService.readPostByBoard(id));
        return "board/read";
    }

    @GetMapping("boardAll")
    public String boardAll(Model model){
        model.addAttribute("posts", postService.readPostAll(Sort.by(Sort.Direction.DESC, "id")));
        return "board/boardAll";
    }

}
