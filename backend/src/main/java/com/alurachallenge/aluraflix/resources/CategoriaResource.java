package com.alurachallenge.aluraflix.resources;

import com.alurachallenge.aluraflix.dto.CategoriaDTO;
import com.alurachallenge.aluraflix.dto.VideoDTO;
import com.alurachallenge.aluraflix.services.CategoriaService;
import com.alurachallenge.aluraflix.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @Autowired
    private VideoService videoService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> categorias = service.findAll();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
        CategoriaDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}/videos")
    public ResponseEntity<List<VideoDTO>> findVideoByCategoria(@PathVariable Long id) {
        List<VideoDTO> dto = videoService.findVideoByCategoria(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> insert(@Valid @RequestBody CategoriaDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CategoriaDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
