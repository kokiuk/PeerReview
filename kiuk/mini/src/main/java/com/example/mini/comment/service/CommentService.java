package com.example.mini.comment.service;

import com.example.mini.comment.entity.Comment;
import com.example.mini.comment.repo.CommentRepo;
import com.example.mini.post.entity.Post;
import com.example.mini.post.repo.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;

    public List<Comment> readCommentAll(){
        return commentRepo.findAll();
    }

    public List<Comment> postComment(Long id){
        return commentRepo.findByPost_Id(id);
    }

    public void createComment(
            String content,
            String password,
            Long postId
    ){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPassword(password);
        Optional<Post> optionalPost =
                postRepo.findById(postId);
        comment.setPost(optionalPost.orElse(null));
        commentRepo.save(comment);

    }

    public Comment findOneComment(Long id){
        Optional<Comment> optionalComment
                = commentRepo.findById(id);

        return optionalComment.orElse(null);
    }

    public void deleteComment(Long id){
        commentRepo.deleteById(id);
    }
}
