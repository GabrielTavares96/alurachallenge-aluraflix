package com.alurachallenge.aluraflix.services;

import com.alurachallenge.aluraflix.dto.VideoDTO;
import com.alurachallenge.aluraflix.dto.VideoInsertDTO;
import com.alurachallenge.aluraflix.entities.Categoria;
import com.alurachallenge.aluraflix.entities.Video;
import com.alurachallenge.aluraflix.repositories.CategoriaRepository;
import com.alurachallenge.aluraflix.repositories.VideoRepository;
import com.alurachallenge.aluraflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public VideoDTO findById(Long id) {
        Optional<Video> video = repository.findById(id);
        Video entity = video.orElseThrow(() -> new ResourceNotFoundException(id));
        return new VideoDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<VideoDTO> findAll(PageRequest pageRequest) {
        Page<Video> videos = repository.findAll(pageRequest);
        return videos.map(x -> new VideoDTO(x));
    }

    @Transactional(readOnly = true)
    public List<VideoDTO> findVideoByCategoria(Long id) {
        List<Video> videos = repository.findVideoByCategoriaId(id);
        return videos.stream().map(x -> new VideoDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VideoDTO> findVideoByTitulo(String titulo) {
        List<Video> videos = repository.findVideoBytitulo(titulo);
        return videos.stream().map(x -> new VideoDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public VideoInsertDTO insert(VideoInsertDTO dto) {

        Video entity = new Video();

        entity.setDescricao(dto.getDescricao());
        entity.setTitulo(dto.getTitulo());
        entity.setUrl(dto.getUrl());

        Categoria categoria = categoriaRepository.getById(dto.getCategoriaId());
        entity.setCategoria(categoria);

        entity = repository.save(entity);

        return new VideoInsertDTO(entity);
    }

    @Transactional
    public VideoInsertDTO update(Long id, VideoInsertDTO dto) {
        try {
            Video entity = repository.getById(id);
            entity.setDescricao(dto.getDescricao());
            entity.setTitulo(dto.getTitulo());
            entity.setUrl(dto.getUrl());

            Categoria categoria = categoriaRepository.getById(dto.getCategoriaId());
            entity.setCategoria(categoria);

            entity = repository.save(entity);

            return new VideoInsertDTO(entity);
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
