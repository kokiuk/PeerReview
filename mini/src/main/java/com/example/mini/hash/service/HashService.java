package com.example.mini.hash.service;

import com.example.mini.hash.entity.Hash;
import com.example.mini.hash.repo.HashRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HashService {
    @Autowired
    private HashRepo hashRepo;

    public Hash createHash(String hashWord){
        Hash hash = new Hash();
        hash.setHashWord(hashWord);
        return hashRepo.save(hash);
    }
    public Hash findByHashWord(String hashWord){
        return hashRepo.findByHashWord(hashWord);
    }

}
