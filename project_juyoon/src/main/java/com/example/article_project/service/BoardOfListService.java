package com.example.article_project.service;

import com.example.article_project.entity.BoardOfList;
import com.example.article_project.repository.BoardOfListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardOfListService {
    private final BoardOfListRepository boardOfListRepository;

    public BoardOfList readBoardOfList(Long id) {
        return boardOfListRepository.findById(id).orElse(null);
    }

    public List<BoardOfList> readBoardOfListAll() {
        return boardOfListRepository.findAll();
    }
}
