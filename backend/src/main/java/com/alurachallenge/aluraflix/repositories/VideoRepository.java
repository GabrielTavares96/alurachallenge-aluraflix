package com.alurachallenge.aluraflix.repositories;

import com.alurachallenge.aluraflix.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

}
