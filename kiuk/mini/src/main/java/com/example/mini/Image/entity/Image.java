package com.example.mini.Image.entity;

import com.example.mini.post.entity.Post;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uploadFileName;
    private String saveFileName;
    private String path;
    private long size;
    private String extension;

    @ManyToOne
    @JoinColumn
    private Post post;

}
