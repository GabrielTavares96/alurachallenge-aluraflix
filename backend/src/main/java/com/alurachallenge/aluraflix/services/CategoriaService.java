package com.alurachallenge.aluraflix.services;

import com.alurachallenge.aluraflix.dto.CategoriaDTO;
import com.alurachallenge.aluraflix.entities.Categoria;
import com.alurachallenge.aluraflix.repositories.CategoriaRepository;
import com.alurachallenge.aluraflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        List<Categoria> categorias = repository.findAll();
        return categorias.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        Optional<Categoria> categoria = repository.findById(id);
        Categoria entity = categoria.orElseThrow(() -> new ResourceNotFoundException(id));
        return new CategoriaDTO(entity);
    }

    @Transactional
    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria entity = new Categoria();

        entity.setTitulo(dto.getTitulo());
        entity.setCor(dto.getCor());

        entity = repository.save(entity);

        return new CategoriaDTO(entity);
    }

    @Transactional
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
