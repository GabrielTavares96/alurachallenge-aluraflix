package com.alurachallenge.aluraflix.services;

import com.alurachallenge.aluraflix.entities.Video;
import com.alurachallenge.aluraflix.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;


    public List<Video> findAllVideos() {
        List<Video> videos = repository.findAll();
        return videos;
    }
}
