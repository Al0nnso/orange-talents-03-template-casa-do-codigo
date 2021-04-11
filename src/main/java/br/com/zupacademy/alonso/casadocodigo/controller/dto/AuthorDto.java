package br.com.zupacademy.alonso.casadocodigo.controller.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.alonso.casadocodigo.model.Author;

public class AuthorDto {

	//private int id;
	private String nome;
	private String email;
	private String descricao;
	private LocalDateTime data;

	public AuthorDto(Author author){
		//this.id=author.getId();
		this.email = author.getEmail();
		this.descricao = author.getDescricao();
		this.nome = author.getNome();
		this.data = author.getData();
	}
	/*public int getId() {
		return this.id;
	}*/

	public String getNome() {
		return this.nome;
	}

	public String getEmail() {
		return this.email;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public LocalDateTime getData(){
		return this.data;
	}
}
