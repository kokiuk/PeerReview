package com.example.mini.post.service;

import com.example.mini.board.entity.Board;
import com.example.mini.board.repo.BoardRepo;
import com.example.mini.comment.repo.CommentRepo;
import com.example.mini.hash.entity.Hash;
import com.example.mini.post.entity.Post;
import com.example.mini.post.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepo postRepo;
    private final BoardRepo boardRepo;
    private final CommentRepo commentRepo;

    public List<Post> readPostByBoard(Long id){
        return postRepo.findByBoard_IdOrderByIdDesc(id);
    }

    public Post readPostOne(Long id){
        Optional<Post> optionalPost =
                postRepo.findById(id);
        return optionalPost.orElse(null);
    }

    public void createPost(
            String title,
            String content,
            String passwd,
            Long boardId,
            List<Hash> hashtags
    ){
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setPassword(passwd);
        Optional<Board> optionalBoard
                = boardRepo.findById(boardId);
        post.setBoard(optionalBoard.orElse(null));
        post.setHashtags(hashtags);
        postRepo.save(post);
    }

    public void updatePost(
            Long id,
            String title,
            String content,
            String passwd,
            Long boardId
    ){
        Post target = readPostOne(id);
        target.setTitle(title);
        target.setContent(content);
        target.setPassword(passwd);
        Optional<Board> optionalBoard
                = boardRepo.findById(boardId);
        target.setBoard(optionalBoard.orElse(null));
        postRepo.save(target);
    }

    public void deletePost(
            Long id
    ){
        postRepo.deleteById(id);
    }

    public List<Post> readPostAll(Sort sort){
        return postRepo.findAll(sort);
    }

    public List<Post> searchPost(String titleOrContent, String detail, Long id){
        if (titleOrContent.equals("title")){
            return postRepo.findByBoard_IdAndAndTitleContainingOrderByIdDesc(id, detail);
        }
        return postRepo.findByBoard_IdAndAndContentContainingOrderByIdDesc(id, detail);
    }

    public List<Post> searchPostAll(String titleOrContent, String detail){
        if (titleOrContent.equals("title")){
            return postRepo.findByTitleContainingOrderByIdDesc(detail);
        }
        return postRepo.findByContentContainingOrderByIdDesc(detail);
    }

    public List<Post> findPostByHash(Hash hash){
        return postRepo.findByHashtagsOrderByIdDesc(hash);
    }

}
