package com.example.mini.post.entity;

import com.example.mini.Image.entity.Image;
import com.example.mini.board.entity.Board;
import com.example.mini.comment.entity.Comment;
import com.example.mini.hash.entity.Hash;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //게시판
    @ManyToOne
    @JoinColumn
    private Board board;

    private String title;
    private String content;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "post_hash",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "hash_id")
    )
    private List<Hash> hashtags;

    @OneToMany(mappedBy = "post")
    private List<Comment> comment;

    @OneToMany(mappedBy = "post")
    private List<Image> uploadImage;


}
