package br.com.zupacademy.alonso.casadocodigo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
//@Table(name="countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Deprecated
    public Country(){
    }

    public Country(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
