package com.example.article_project.repository;

import com.example.article_project.entity.Board;
import com.example.article_project.entity.BoardOfList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardOfListRepository extends JpaRepository<BoardOfList, Long> {

}
