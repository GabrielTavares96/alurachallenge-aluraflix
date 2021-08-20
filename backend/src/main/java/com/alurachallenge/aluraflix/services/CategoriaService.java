package com.alurachallenge.aluraflix.services;

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

    public Categoria findById(Long id) {
        Optional<Categoria> categoria = repository.findById(id);
        return categoria.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Categoria insert(Categoria categoria) {
        Categoria entity = repository.save(categoria);
        return entity;
    }

    public Categoria update(Long id, Categoria obj) {
        try {
            Categoria entity = repository.getById(id);
            entity.setTitulo(obj.getTitulo());
            entity.setCor(obj.getCor());
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
