package com.example.mini.comment.controller;

import com.example.mini.board.service.BoardService;
import com.example.mini.comment.service.CommentService;
import com.example.mini.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final BoardService boardService;
    private final PostService postService;

    @PostMapping("create")
    public String create(
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password,
            @RequestParam("postId")
            Long postId
    ){
        commentService.createComment(content, password, postId);
        return String.format("redirect:/post/read/%d",postId);
    }

    @GetMapping("deleteCheck/{id}")
    public String deleteCheck(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("comments", commentService.findOneComment(id));
        return "/comment/deletePasswdCheck";
//        Long postId = commentService.findOneComment(id).getPost().getId();
//        return String.format("redirect:/post/read/%d",postId);
    }

    @PostMapping("delete")
    public String delete(
            @RequestParam("id")
            Long id,
            @RequestParam("passwd")
            String passwd

    ){
        if (passwd.equals(commentService.findOneComment(id).getPassword())){
            Long postId = commentService.findOneComment(id).getPost().getId();
            commentService.deleteComment(id);
            return String.format("redirect:/post/read/%d",postId);
        }
        else {
            return "/passwdErrorPage";
        }
    }
}
