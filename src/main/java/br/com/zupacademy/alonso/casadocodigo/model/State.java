package br.com.zupacademy.alonso.casadocodigo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="states")
public class State {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private Country country;

    @Deprecated
    public State(){
    }

    public State(String name,Country country){
        this.name=name;
        this.country=country;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public Long getId() {
        return id;
    }
}
