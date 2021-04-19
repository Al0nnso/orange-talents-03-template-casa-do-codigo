package br.com.zupacademy.alonso.casadocodigo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String email;
	private String descricao;
	
	@CreationTimestamp
	private LocalDateTime data = LocalDateTime.now();
	
	@Deprecated
	public Author() {
	}

	public Author(String nome, String email, String descricao){
		this.email = email.toLowerCase();
		this.descricao = descricao;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getData(){
		return this.data;
	}
	
	@Override
	public String toString() {
		return "Author [ "+this.getNome()+" - "+this.getEmail()+" / "+this.getDescricao()+" ]";
	}

}
