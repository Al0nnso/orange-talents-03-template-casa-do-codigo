package br.com.zupacademy.alonso.casadocodigo.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.alonso.casadocodigo.model.Country;

public class CountryForm {

    @NotBlank
    private String name;

    @Deprecated
    public CountryForm(){
    }

    public CountryForm(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public Country converter(){
        Country pais = new Country(this.name);
        return pais;
    }
}
