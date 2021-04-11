package br.com.zupacademy.alonso.casadocodigo.controller.dto;

import br.com.zupacademy.alonso.casadocodigo.model.Category;

public class CategoryDto {
    private String nome;

    public CategoryDto(Category category){
        this.nome=category.getNome();
    }

    public String getNome() {
        return nome;
    }
}
