package br.com.zupacademy.alonso.casadocodigo.controller.form;

import java.io.Console;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.alonso.casadocodigo.model.Author;
import br.com.zupacademy.alonso.casadocodigo.model.Category;
import br.com.zupacademy.alonso.casadocodigo.repository.AuthorRepository;

@Component
public class ProibeBookIDsExistem implements Validator{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
    public boolean supports(Class<?> clazz) {
		return BookForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		BookForm form = (BookForm) target;
		System.out.println("sasa");
		System.out.println(manager.find(Author.class,form.getAuthorID()));

        if(form.getAuthorID()!=null && manager.find(Author.class,form.getAuthorID())==null) {
            errors.rejectValue("author", null, "Não existe um author com este id: "+form.getAuthorID());
        }

        if(form.getCategoryID()!=null && manager.find(Category.class,form.getCategoryID())==null) {
            errors.rejectValue("category", null, "Não existe uma category com este id: "+form.getCategoryID());
        }
	}

}
