package com.example.mini.post.repo;

import com.example.mini.hash.entity.Hash;
import com.example.mini.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findByBoard_IdOrderByIdDesc(Long id);

    List<Post> findByBoard_IdAndAndTitleContainingOrderByIdDesc(Long id, String detail);

    List<Post> findByBoard_IdAndAndContentContainingOrderByIdDesc(Long id, String detail);

    List<Post> findByTitleContainingOrderByIdDesc(String detail);
    List<Post> findByContentContainingOrderByIdDesc(String detail);

    List<Post> findByHashtagsOrderByIdDesc(Hash hash);
}
