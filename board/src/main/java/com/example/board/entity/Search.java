package com.example.board.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "search")
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sch;
}
