package com.example.board.controller;

import com.example.board.service.ArticleService;
import com.example.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;
    private final ArticleService articleService;

    // 댓글 쓰기
    @GetMapping("create_view/{id}")
    public String createCommentView(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("article",articleService.readArticle(id));
        return "comment/create_comment";
    }
    @PostMapping("create_comment/{id}")
    public String createComment(
            @PathVariable("id")
            Long id,
            @RequestParam("comment")
            String comment,
            @RequestParam("password")
            String password
    ){
        commentService.createComment(comment, password, id);
        return String.format("redirect:/board/article/%d",id);
    }

    // 댓글 수정
    @GetMapping("update_view/{id}/{articleId}")
    public String updateCommentView(
            @PathVariable("id")
            Long id,
            @PathVariable("articleId")
            Long articleId,
            Model model
    ){
        model.addAttribute("comment",commentService.readComment(id));
        model.addAttribute("article",articleService.readArticle(articleId));
        return "comment/update_comment";
    }
    @PostMapping("update/{id}/{articleId}/{prePassword}")
    public String updateArticle(
            @PathVariable("id")
            Long id,
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("prePassword")
            String prePassword,
            @RequestParam("comment")
            String comment,
            @RequestParam("password")
            String password
    ){
        if(password.equals(prePassword)){
            commentService.updateComment(id,comment,password);
            return String.format("redirect:/board/article/%d",articleId);
        }
        return String.format("redirect:/board/article/%d",articleId);
    }

    //댓글 삭제
    @PostMapping("delete/{id}/{articleId}/{prePassword}")
    public String deleteComment(
            @PathVariable("id")
            Long id,
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("prePassword")
            String prePassword,
            @RequestParam("password")
            String password
    ){
        if(prePassword.equals(password)) {
            commentService.deleteComment(id);
            return String.format("redirect:/board/article/%d", articleId);
        }
        else return String.format("redirect:/board/article/%d", articleId);
    }
}
