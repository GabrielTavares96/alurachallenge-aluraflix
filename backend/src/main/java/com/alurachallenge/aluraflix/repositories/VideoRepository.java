package com.alurachallenge.aluraflix.repositories;

import com.alurachallenge.aluraflix.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findVideoByCategoriaId(Long id);
}
