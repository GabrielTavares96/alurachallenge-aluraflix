package com.alurachallenge.aluraflix.resources;

import com.alurachallenge.aluraflix.dto.VideoDTO;
import com.alurachallenge.aluraflix.dto.VideoInsertDTO;
import com.alurachallenge.aluraflix.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<VideoDTO> findById(@PathVariable Long id) {
        VideoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<VideoDTO>> findVideoByCategoria
            (
                    @RequestParam(value = "search", required = true) String titulo
            ) {
        List<VideoDTO> dto = service.findVideoByTitulo(titulo);
        return ResponseEntity.ok(dto);
    }


    @GetMapping
    public ResponseEntity<Page<VideoDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "titulo") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<VideoDTO> videos = service.findAll(pageRequest);
        return ResponseEntity.ok(videos);
    }

    @PostMapping
    public ResponseEntity<VideoInsertDTO> insert(@Valid @RequestBody VideoInsertDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VideoInsertDTO> update(@PathVariable Long id, @Valid @RequestBody VideoInsertDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<VideoInsertDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
