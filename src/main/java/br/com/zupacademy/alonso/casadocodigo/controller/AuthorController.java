package br.com.zupacademy.alonso.casadocodigo.controller;

import java.time.DateTimeException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.alonso.casadocodigo.controller.dto.AuthorDto;
import br.com.zupacademy.alonso.casadocodigo.controller.form.AuthorForm;
import br.com.zupacademy.alonso.casadocodigo.model.Author;
import br.com.zupacademy.alonso.casadocodigo.repository.AuthorRepository;

@Controller
public class AuthorController {
	
	private final AuthorRepository repository;

	public AuthorController(AuthorRepository repository) {
		this.repository = repository;
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid AuthorForm form) {
		/*
		Author author = new Author();
		author.setNome(name);
		author.setEmail(email);
		author.setDescricao(descricao);
		*/
		Author author = form.converter();
		repository.save(author);
		return ResponseEntity.ok().build();
	}
	
}
