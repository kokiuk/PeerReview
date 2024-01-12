package com.example.article_project.repository;

import com.example.article_project.entity.Board;
import com.example.article_project.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByBoardOrderByIdDesc(Board board);
}
