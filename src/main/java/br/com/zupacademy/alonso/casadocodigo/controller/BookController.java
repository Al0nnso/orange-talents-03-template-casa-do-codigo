package br.com.zupacademy.alonso.casadocodigo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.alonso.casadocodigo.controller.dto.BookDto;
import br.com.zupacademy.alonso.casadocodigo.controller.form.BookForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.ProibeBookIDsExistem;
import br.com.zupacademy.alonso.casadocodigo.model.Book;
import br.com.zupacademy.alonso.casadocodigo.repository.BookRepository;

@RestController
public class BookController {

	//private final BookRepository repository;

	/*public BookController(BookRepository repository) {
		this.repository = repository;
	}*/

    @PersistenceContext
    private EntityManager manager;

    
    /*@Autowired
    private ProibeBookIDsExistem proibeBookIDsExistem;
    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeBookIDsExistem);
    }*/
    
    
    @PostMapping("/livro")
    @Transactional
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookForm form){
        Book book = form.converter(manager);
        //repository.save(book);
        manager.persist(book);
        BookDto response = new BookDto(book);
        return ResponseEntity.ok(response);
    }
}
