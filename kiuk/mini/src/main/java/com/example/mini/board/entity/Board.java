package com.example.mini.board.entity;

import com.example.mini.post.entity.Post;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String board;

    @OneToMany(mappedBy = "board")
    private List<Post> post;


}
