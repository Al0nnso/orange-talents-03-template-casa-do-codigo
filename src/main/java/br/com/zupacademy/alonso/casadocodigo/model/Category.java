package br.com.zupacademy.alonso.casadocodigo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private String nome;

    public Category(String nome){
        this.nome=nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
