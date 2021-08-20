package com.alurachallenge.aluraflix.services;

import com.alurachallenge.aluraflix.entities.Categoria;
import com.alurachallenge.aluraflix.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<Categoria> findAll() {
        List<Categoria> categorias = repository.findAll();
        return categorias;
    }
}
