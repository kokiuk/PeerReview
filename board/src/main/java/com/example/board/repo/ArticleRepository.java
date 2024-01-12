package com.example.board.repo;

import com.example.board.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByBoardId(Long id);
    List<Article> findAllByOrderByIdDesc();
    List<Article> findByBoardIdOrderByIdDesc(Long id);
}
