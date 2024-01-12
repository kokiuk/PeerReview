package com.example.communityboard.service;

import com.example.communityboard.entity.Board;
import com.example.communityboard.entity.Community;
import com.example.communityboard.repo.BoardRepository;
import com.example.communityboard.repo.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommunityRepository communityRepository;


    public Board readBoard(Long id){
        return boardRepository.findById(id).orElse(null);
    }
    public List<Board> readBoardAll(){
        return boardRepository.findAll();
    }
}
