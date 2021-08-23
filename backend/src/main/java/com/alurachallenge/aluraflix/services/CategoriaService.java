package com.alurachallenge.aluraflix.services;

import com.alurachallenge.aluraflix.dto.CategoriaDTO;
import com.alurachallenge.aluraflix.entities.Categoria;
import com.alurachallenge.aluraflix.repositories.CategoriaRepository;
import com.alurachallenge.aluraflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> findAll() {
        List<Categoria> categorias = repository.findAll();
        return categorias;
    }

    public CategoriaDTO findById(Long id) {
        Optional<Categoria> categoria = repository.findById(id);
        Categoria entity = categoria.orElseThrow(() -> new ResourceNotFoundException(id));
        return new CategoriaDTO(entity);
    }

    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria entity = new Categoria();

        entity.setTitulo(dto.getTitulo());
        entity.setCor(dto.getCor());

        entity = repository.save(entity);

        return new CategoriaDTO(entity);
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        try {
            Categoria entity = repository.getById(id);
            entity.setTitulo(dto.getTitulo());
            entity.setCor(dto.getCor());
            entity = repository.save(entity);

            return new CategoriaDTO(entity);
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
