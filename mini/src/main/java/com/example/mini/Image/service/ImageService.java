package com.example.mini.Image.service;

import com.example.mini.Image.entity.Image;
import com.example.mini.Image.repo.ImageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {
    ImageRepo imageRepo;

    public void saveImage(Image image){
        Image i = new Image();
        i.setSaveFileName(image.getSaveFileName());
    }
}
