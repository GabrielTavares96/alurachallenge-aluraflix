package com.alurachallenge.aluraflix.resources;

import com.alurachallenge.aluraflix.entities.Video;
import com.alurachallenge.aluraflix.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/videos")
public class VideoResource {

    @Autowired
    private VideoService service;

    @GetMapping
    public ResponseEntity<List<Video>> findAll() {
        List<Video> videos = service.findAllVideos();
        return ResponseEntity.ok(videos);
    }
}