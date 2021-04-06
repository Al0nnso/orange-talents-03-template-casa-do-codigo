package br.com.zupacademy.alonso.casadocodigo.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.alonso.casadocodigo.model.Author;

public class AuthorForm {
	
	@NotBlank @Length(min = 5)
	private String nome;
	@NotBlank @Length(min = 5,max = 300)
	private String descricao;
	@NotBlank
	private String email;
	
	public AuthorForm(String nome, String email, String descricao){
		this.email = email.toLowerCase();
		this.descricao = descricao;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getEmail() {
		return email;
	}
	
	public Author converter() {
		Author author = new Author(this.nome,this.email,this.descricao);
		return author;
	}
	

}
