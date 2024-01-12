package com.example.board.service;

import com.example.board.entity.Comment;
import com.example.board.repo.ArticleRepository;
import com.example.board.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    // 게시글의id를 가지고 있는 댓글들을 찾는 메서드
    // .findByArticleId를 CommentRepository에 정의해놨습니다.
    public List<Comment> getComments(Long id){return commentRepository.findByArticleId(id);}

    // 댓글의 id로 해당되는 댓글을 찾는 메서드
    public Comment readComment(Long id){ return commentRepository.findById(id).orElse(null);}

    // 댓글을 만드는 메서드
    public void createComment(
            String comment,
            String password,
            Long articleId
    ){
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        comment1.setPassword(password);
        comment1.setArticleId(articleId);
        comment1.setArticleName(articleRepository.findById(articleId).orElse(null));
        commentRepository.save(comment1);
    }

    // 댓글을 업데이트 하는 메서드
    public void updateComment(
            Long id,
            String comment,
            String password
    ){
        Comment target = commentRepository.findById(id).orElse(new Comment());
        target.setComment(comment);
        target.setPassword(password);
        commentRepository.save(target);
    }

    // 댓글을 삭제하는 메서드
    public void deleteComment(Long id){commentRepository.deleteById(id);}
}
