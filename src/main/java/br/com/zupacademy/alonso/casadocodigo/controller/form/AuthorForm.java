package br.com.zupacademy.alonso.casadocodigo.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.alonso.casadocodigo.model.Author;

public class AuthorForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	@NotNull @NotEmpty @Length(min = 5,max = 300)
	private String descricao;
	@NotNull @NotEmpty @Length(min = 5)
	private String email;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Author converter() {
		Author author = new Author();
		author.setNome(this.nome);
		author.setEmail(this.email);
		author.setDescricao(this.descricao);
		return author;
	}
	

}
