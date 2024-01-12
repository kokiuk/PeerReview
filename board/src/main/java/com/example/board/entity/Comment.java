package com.example.board.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String comment;
    private Long articleId;
    @ManyToOne
    @JoinColumn
    private Article articleName;
}
