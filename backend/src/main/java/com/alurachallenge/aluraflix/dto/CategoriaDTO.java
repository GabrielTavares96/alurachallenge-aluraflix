package com.alurachallenge.aluraflix.dto;

import com.alurachallenge.aluraflix.entities.Categoria;

import javax.validation.constraints.NotBlank;

public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String titulo;

    @NotBlank(message = "Campo obrigatório")
    private String cor;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria entity) {
        this.id = entity.getId();
        this.titulo = entity.getTitulo();
        this.cor = entity.getCor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
