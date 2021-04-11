package br.com.zupacademy.alonso.casadocodigo.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.alonso.casadocodigo.model.Category;

public class CategoryForm {
    @NotBlank
    private String nome;

    @Deprecated
    public CategoryForm(){
    }

    public CategoryForm(String nome){
        this.nome=nome;
    }

    public String getNome() {
        return nome;
    }

    public Category converter(){
        Category category = new Category(this.nome);
        return category;
    }
}
