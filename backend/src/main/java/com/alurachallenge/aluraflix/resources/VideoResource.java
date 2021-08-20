package com.alurachallenge.aluraflix.resources;

import com.alurachallenge.aluraflix.entities.Video;
import com.alurachallenge.aluraflix.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/videos")
public class VideoResource {

    @Autowired
    private VideoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Video> findById(@PathVariable Long id) {
        Video video = service.findById(id);
        return ResponseEntity.ok(video);
    }

    @GetMapping
    public ResponseEntity<List<Video>> findAll() {
        List<Video> videos = service.findAll();
        return ResponseEntity.ok(videos);
    }

    @PostMapping
    public ResponseEntity<Video> insert(@Valid @RequestBody Video video) {
        video = service.insert(video);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(video);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Video> update(@PathVariable Long id, @Valid @RequestBody Video video) {
        video = service.update(id, video);
        return ResponseEntity.ok(video);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Video> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
