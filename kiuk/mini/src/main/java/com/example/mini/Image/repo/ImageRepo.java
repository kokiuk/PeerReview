package com.example.mini.Image.repo;

import com.example.mini.Image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
