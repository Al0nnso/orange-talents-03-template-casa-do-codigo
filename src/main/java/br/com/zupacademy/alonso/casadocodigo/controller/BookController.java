package br.com.zupacademy.alonso.casadocodigo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.alonso.casadocodigo.controller.form.BookForm;
import br.com.zupacademy.alonso.casadocodigo.model.Book;
import br.com.zupacademy.alonso.casadocodigo.repository.BookRepository;

@RestController
public class BookController {

    //@Autowired
	private final BookRepository repository;
	// A mesma coisa que @Autowired
	public BookController(BookRepository repository) {
		this.repository = repository;
	}
    
    @PostMapping("/livro")
    public ResponseEntity<Book> createBook(@RequestBody @Valid BookForm form){
        Book book = form.converter();
        repository.save(book);
        return ResponseEntity.ok(book);
    }
}
