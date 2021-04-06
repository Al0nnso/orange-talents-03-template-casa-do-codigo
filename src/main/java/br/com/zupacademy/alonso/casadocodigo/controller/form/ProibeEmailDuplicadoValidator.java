package br.com.zupacademy.alonso.casadocodigo.controller.form;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.alonso.casadocodigo.model.Author;
import br.com.zupacademy.alonso.casadocodigo.repository.AuthorRepository;

@Component
public class ProibeEmailDuplicadoValidator implements Validator{

	@Autowired
	private AuthorRepository repository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return AuthorForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		AuthorForm form = (AuthorForm) target;
		
		//Optional<Author> authorEmail = repository.findByEmail(form.getEmail());
		Boolean existsEmail = repository.existsByEmail(form.getEmail());
		
		if(existsEmail) {
			errors.rejectValue("email", null, "Já existe um author com o mesmo email: "+form.getEmail());
		}
	}

}
