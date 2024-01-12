package com.example.mini.hash.repo;

import com.example.mini.hash.entity.Hash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashRepo extends JpaRepository<Hash, Long> {
    public Hash findByHashWord(String hashWord);
}
