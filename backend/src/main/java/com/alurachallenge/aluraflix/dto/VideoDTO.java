package com.alurachallenge.aluraflix.dto;


import com.alurachallenge.aluraflix.entities.Video;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class VideoDTO {

    private Long id;

    @Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String titulo;

    @NotBlank(message = "Campo obrigatório")
    private String descricao;

    @NotBlank(message = "Campo obrigatório")
    private String url;

    private Long categoriaId;

    @NotBlank(message = "Campo obrigatório")
    private String categoriaTitulo;

    @NotBlank(message = "Campo obrigatório")
    private String categoriaCor;

    public VideoDTO() {
    }

    public VideoDTO(Video entity) {
        this.id = entity.getId();
        this.titulo = entity.getTitulo();
        this.descricao = entity.getDescricao();
        this.url = entity.getUrl();
        this.categoriaId = entity.getCategoria().getId();
        this.categoriaTitulo = entity.getCategoria().getTitulo();
        this.categoriaCor = entity.getCategoria().getCor();
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaTitulo() {
        return categoriaTitulo;
    }

    public void setCategoriaTitulo(String categoriaTitulo) {
        this.categoriaTitulo = categoriaTitulo;
    }

    public String getCategoriaCor() {
        return categoriaCor;
    }

    public void setCategoriaCor(String categoriaCor) {
        this.categoriaCor = categoriaCor;
    }
}
