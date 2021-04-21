package br.com.zupacademy.alonso.casadocodigo.controller.form;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.zupacademy.alonso.casadocodigo.model.Country;
import br.com.zupacademy.alonso.casadocodigo.model.State;

public class StateForm {
    
    @NotBlank
    private String name;
    private Long countryID;

    @Deprecated
    public StateForm(){
    }

    public StateForm(String name, Long countryID){
        this.name=name;
        this.countryID=countryID;
    }

    public String getName() {
        return name;
    }

    public Long getCountryID() {
        return countryID;
    }

    @Transactional
    public State converter(EntityManager manager){
        
        Country country = manager.find(Country.class,this.countryID);
        Assert.state(country!=null,"O country ID é nulo: "+this.countryID);

        State state = new State(this.name,country);
        return state;
    }

}
