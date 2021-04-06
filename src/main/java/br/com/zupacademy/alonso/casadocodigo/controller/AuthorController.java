package br.com.zupacademy.alonso.casadocodigo.controller;

import java.time.DateTimeException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.alonso.casadocodigo.controller.dto.AuthorDto;
import br.com.zupacademy.alonso.casadocodigo.controller.form.AuthorForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.ProibeEmailDuplicadoValidator;
import br.com.zupacademy.alonso.casadocodigo.model.Author;
import br.com.zupacademy.alonso.casadocodigo.repository.AuthorRepository;

@RestController
public class AuthorController {
	
	@Autowired
	private AuthorRepository repository;
	
	@Autowired
	private ProibeEmailDuplicadoValidator proibeEmailDuplicadoValidator;

	/*
	// A mesma coisa que @Autowired
	public AuthorController(AuthorRepository repository) {
		this.repository = repository;
	}
	*/
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeEmailDuplicadoValidator);
	}

	@PostMapping("/add")
	public String createAuthor(@RequestBody @Valid AuthorForm form) {

		Author author = form.converter();

		repository.save(author);
		return author.toString();
	}
	
}
