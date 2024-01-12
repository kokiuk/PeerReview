package com.example.mini.comment.entity;

import com.example.mini.post.entity.Post;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //게시글
    @ManyToOne
    @JoinColumn
    private Post post;

    private String content;
    private String password;
}
