package com.example.board.controller;

import org.springframework.ui.Model;
import com.example.board.service.ArticleService;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("board")
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;

    // 전체 게시판
    @GetMapping
    public String home(Model model){
        model.addAttribute("articles", articleService.readBoardAllDesc());
        model.addAttribute("boards",articleService.getBoardAll());
        return "board/home_board";
    }

    // 세부 게시판
    @GetMapping("{BoardId}")
    public String readBoard(
            @PathVariable("BoardId")
            Long BoardId,
            Model model
    ){
        model.addAttribute("boards",articleService.readBoardDesc(BoardId));
        model.addAttribute("board", articleService.getBoard(BoardId));
        return "board/read_board";
    }

    // 게시글 쓰기
    @GetMapping("create_view")
    public String createArticleView(Model model){
        model.addAttribute("boards",articleService.getBoardAll());
        return "article/create_article";
    }
    @PostMapping("create_article")
    public String createArticle(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password,
            @RequestParam("boardId")
            Long boardId
    ){
        articleService.createArticle(title,content,password,boardId);
        return "redirect:/board";
    }

    //게시글 보기
    @GetMapping("article/{id}")
    public String readArticle(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("article",articleService.readArticle(id));
        model.addAttribute("comments",commentService.getComments(id));
        return "article/read_article";
    }

    // 게시글 수정
    @GetMapping("article/{id}/update_view")
    public String updateArticleView(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("article",articleService.readArticle(id));
        model.addAttribute("boards",articleService.getBoardAll());
        return "article/update_article";
    }
    @PostMapping("article/{id}/update_article/{prePassword}")
    public String updateArticle(
            @PathVariable("id")
            Long id,
            @PathVariable("prePassword")
            String prePassword,
            @RequestParam("title")
            String title,
            @RequestParam("password")
            String password,
            @RequestParam("content")
            String content,
            @RequestParam("boardId")
            Long boardId
    ){
        if(prePassword.equals(password)){
            articleService.updateArticle(id,title,content,password,boardId);
            return String.format("redirect:/board/article/%d",id);
        }
        else return String.format("redirect:/board/article/%d",id);
    }

    //게시글 삭제
    @PostMapping("article/{id}/delete_article/{prePassword}")
    public String deleteArticle(
            @PathVariable("id")
            Long id,
            @PathVariable("prePassword")
            String prePassword,
            @RequestParam("password")
            String password
    ){
        if(prePassword.equals(password)){
            articleService.delete(id);
            return "redirect:/board";
        }
        return String.format("redirect:/board/article/%d",id);
    }
}
