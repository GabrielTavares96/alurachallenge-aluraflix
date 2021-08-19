package com.alurachallenge.aluraflix.services;

import com.alurachallenge.aluraflix.entities.Video;
import com.alurachallenge.aluraflix.repositories.VideoRepository;
import com.alurachallenge.aluraflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    public Video findById(Long id) {
        Optional<Video> video = repository.findById(id);
        return video.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Video> findAll() {
        List<Video> videos = repository.findAll();
        return videos;
    }

    public Video insert(Video video) {
        Video entity = new Video();
        entity = repository.save(video);
        return entity;
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
