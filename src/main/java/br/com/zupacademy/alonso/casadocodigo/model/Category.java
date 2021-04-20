package br.com.zupacademy.alonso.casadocodigo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nome;

    @Deprecated
    public Category(){
    }

    public Category(String nome){
        this.nome=nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
