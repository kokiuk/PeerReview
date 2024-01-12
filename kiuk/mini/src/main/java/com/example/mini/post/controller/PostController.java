package com.example.mini.post.controller;

import com.example.mini.board.service.BoardService;
import com.example.mini.comment.service.CommentService;
import com.example.mini.hash.entity.Hash;
import com.example.mini.hash.service.HashService;
import com.example.mini.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    @Autowired //안쓰면 에러... 왜??
    private PostService postService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private HashService hashService;

    @GetMapping("createView")
    public String createView(Model model,
                             @RequestParam("boardId")
                             Long id){
        model.addAttribute("boards", boardService.readBoardAll());
        model.addAttribute("id", id);
//        System.out.println("id = " + id);
        return "post/createView";
    }

    @GetMapping("read/{id}")
    public String readPostOne(
            @PathVariable("id")
            Long id,
            Model model
    ){
//        System.out.println("id = " + id);
        model.addAttribute("posts", postService.readPostOne(id));
        model.addAttribute("comments", commentService.postComment(id));
        return "/post/read";
    }

    @PostMapping("create")
    public String createPost(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("passwd")
            String passwd,
            @RequestParam("board_id")
            Long boardId,
            @RequestParam("hashtags")
            String hashtags
    ){
//        System.out.println(hashtags);

        List<String> hashtagList = Arrays.asList(hashtags.split(","));

        List<Hash> postHashtags = new ArrayList<>();
        for (String hash :
                hashtagList) {
//            System.out.println(hash);
            Hash existHash = hashService.findByHashWord(hash);

            if (existHash != null){
                postHashtags.add(existHash);
            }else {
                Hash createHash = hashService.createHash(hash);
                postHashtags.add(createHash);
            }
        }

        postService.createPost(title, content, passwd, boardId, postHashtags);
        return String.format("redirect:/board/%d", boardId);
    }

    @PostMapping("updateCheck")
    public String updatePasswdCheck(
            @RequestParam("id")
            Long id,
            @RequestParam("passwd")
            String passwd,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            Model model
    ){
        System.out.println(id);
        System.out.println(passwd);
        System.out.println(title);
        System.out.println(content);
        model.addAttribute("posts", postService.readPostOne(id));

        return "post/updatePasswdCheck";
    }


    @PostMapping("update")
    public String update(
            @RequestParam("id")
            Long id,
            @RequestParam("passwd")
            String password,
            Model model

    ){
        if (password.equals(postService.readPostOne(id).getPassword())){
            model.addAttribute("posts", postService.readPostOne(id));
            return String.format("redirect:/post/updateDo/%d",id);
        }else {
            return "/passwdErrorPage";
        }
    }

    @GetMapping("updateDo/{id}")
    public String updateDo(
            @PathVariable("id")
            Long id,
            Model model
    ){
        model.addAttribute("boards", boardService.readBoardAll());
        model.addAttribute("posts", postService.readPostOne(id));
        return "/post/updateDo";
    }

    @PostMapping("updateFinal")
    public String updateFinal(
            @RequestParam("id")
            Long id,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("passwd")
            String password,
            @RequestParam("board_id")
            Long boardId
    ){
        postService.updatePost(id, title, content, password, boardId);
        return String.format("redirect:/post/read/%d", id);
    }

    @PostMapping("deleteCheck")
    public String deletePasswdCheck(
            @RequestParam("id")
            Long id,
            @RequestParam("passwd")
            String passwd,
            Model model
    ){
//        System.out.println(id);
//        System.out.println(passwd);
        model.addAttribute("posts", postService.readPostOne(id));

        return "post/deletePasswdCheck";
    }

    @PostMapping("delete")
    public String delete(
            @RequestParam("id")
            Long id,
            @RequestParam("passwd")
            String password,
            @RequestParam("board")
            Long board
    ){
        if (password.equals(postService.readPostOne(id).getPassword())){

            postService.deletePost(id);
            return String.format("redirect:/board/%d", board);
        }else {
            return "/passwdErrorPage";
        }
    }

    @PostMapping("search")
    public String search(
            @RequestParam("search")
            String titleOrContent,
            @RequestParam("searchDetail")
            String detail,
            @RequestParam("boardId")
            Long id,
            Model model
    ){
        if (id != 0){
//            System.out.println(id);
            model.addAttribute("posts", postService.searchPost(titleOrContent, detail, id));
            model.addAttribute("board", boardService.readBoardOne(id));
            return "/board/read";
        }

//        System.out.println(id);
        model.addAttribute("posts", postService.searchPostAll(titleOrContent, detail));
        return "/board/boardAll";

    }

    @GetMapping("search/{hashtag}")
    public String searchHash(
            @PathVariable("hashtag")
            String hashtag,
            Model model
    ){
        System.out.println(hashtag);

        Hash hash = hashService.findByHashWord(hashtag);
        model.addAttribute("posts", postService.findPostByHash(hash));

        return "/hash/read";
    }




}
