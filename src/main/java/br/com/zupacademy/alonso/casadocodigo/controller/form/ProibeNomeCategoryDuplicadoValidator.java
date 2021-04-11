package br.com.zupacademy.alonso.casadocodigo.controller.form;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.alonso.casadocodigo.repository.CategoryRepository;

@Component
public class ProibeNomeCategoryDuplicadoValidator implements Validator{

	@Autowired
	private CategoryRepository repository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CategoryForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		CategoryForm form = (CategoryForm) target;
		
		//Optional<Author> authorEmail = repository.findByEmail(form.getEmail());
		Boolean existsNome = repository.existsByNome(form.getNome());
		
		if(existsNome) {
			errors.rejectValue("nome", null, "Já existe uma categoria com o mesmo nome: "+form.getNome());
		}
	}

}
