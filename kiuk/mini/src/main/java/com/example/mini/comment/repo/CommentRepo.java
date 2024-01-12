package com.example.mini.comment.repo;

import com.example.mini.comment.entity.Comment;
import com.example.mini.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_Id(Long id);
}
