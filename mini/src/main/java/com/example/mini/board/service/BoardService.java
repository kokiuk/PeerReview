package com.example.mini.board.service;

import com.example.mini.board.entity.Board;
import com.example.mini.board.repo.BoardRepo;
import com.example.mini.post.entity.Post;
import com.example.mini.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepo boardRepo;
    private final PostService postService;

    public Board readBoardOne(Long id){
        return boardRepo.findById(id).orElse(null);
    }

    public List<Board> readBoardAll(){
        return boardRepo.findAll();
    }

    public List<Post> readPostByBoard(Long id){
        return postService.readPostByBoard(id);
    }
}
