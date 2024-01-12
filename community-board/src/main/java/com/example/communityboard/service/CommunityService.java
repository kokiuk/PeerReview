package com.example.communityboard.service;

import com.example.communityboard.entity.Board;
import com.example.communityboard.entity.Community;
import com.example.communityboard.repo.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;


    public void create(
            Long boardId,
            String title,
            String content,
            Integer password
    ){
        Community community = new Community();
        community.setBoard_id(boardId);
        community.setTitle(title);
        community.setContent(content);
        community.setPassword(password);
        communityRepository.save(community);
    }
    public List<Community> readCommunityAll(){
        return communityRepository.findAll();
    }

    public Community readCommunity(Long id){
        return communityRepository.findById(id).orElse(null);
    }

    public void update(
            Long id,
            Long boardId,
            String title,
            String content,
            Integer password
    ){
        Community target
                = communityRepository.findById(id).orElse(new Community());
        target.setBoard_id(boardId);
        target.setTitle(title);
        target.setContent(content);
        target.setPassword(password);
        communityRepository.save(target);
    }

    public void delete(Long id){
        communityRepository.deleteById(id);
    }
    public Long boardID(Long id){
        Community target
                =communityRepository.findById(id).orElse(new Community());
        return target.getBoard_id();
    }
}
