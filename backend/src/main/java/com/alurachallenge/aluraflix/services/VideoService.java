package com.alurachallenge.aluraflix.services;

import com.alurachallenge.aluraflix.entities.Video;
import com.alurachallenge.aluraflix.repositories.VideoRepository;
import com.alurachallenge.aluraflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        Video entity = repository.save(video);
        return entity;
    }

    public Video update(Long id, Video obj) {
        try {
            Video entity = repository.getById(id);
            entity.setDescricao(obj.getDescricao());
            entity.setTitulo(obj.getTitulo());
            entity.setUrl(obj.getUrl());

            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
