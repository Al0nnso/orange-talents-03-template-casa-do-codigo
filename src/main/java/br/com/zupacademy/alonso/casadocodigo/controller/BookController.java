package br.com.zupacademy.alonso.casadocodigo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.alonso.casadocodigo.controller.dto.BookDto;
import br.com.zupacademy.alonso.casadocodigo.controller.dto.BookListDto;
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

    @GetMapping("/livro")
    public List<BookListDto> listBooks(){
        //Book book1 = new Book("aaaaa", "aaaaa", 423.99, 342, "bomdiaecia");
        //List bookList = manager.createQuery("SELECT id,title FROM books").getResultList();
        //System.out.print(bookList);
        return manager.createQuery("from Book",Book.class).getResultList()
        .stream().map(book-> new BookListDto(book)).collect(Collectors.toList());

        //List<BookDto> books = new ArrayList<BookDto>();
        //books.add(new BookDto(book1));
        //return books;
    }

    @GetMapping("/livro/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable Long bookId){
        Book book = manager.find(Book.class, bookId);
        if(book!=null){
            BookDto response = new BookDto(book);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    } 
}
