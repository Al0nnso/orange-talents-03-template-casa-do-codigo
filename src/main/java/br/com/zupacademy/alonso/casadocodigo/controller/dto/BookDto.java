package br.com.zupacademy.alonso.casadocodigo.controller.dto;

import java.time.LocalDate;
import br.com.zupacademy.alonso.casadocodigo.model.Book;

public class BookDto {
    private Long id;
    private String title;
    private String summary;
    private Double price;
    private Integer pages;
    private String isbn;
    private LocalDate release;
    private AuthorDto author;
    private CategoryDto category;
    
    public BookDto(Book book){
        this.id=book.getId();
        this.title=book.getTitle();
        this.summary=book.getSummary();
        this.price=book.getPrice();
        this.pages=book.getPages();
        this.isbn=book.getIsbn();
        this.release=book.getRelease();
        
        if(book.getAuthor()!=null){
            this.author=new AuthorDto(book.getAuthor());
        }
        if(book.getCategory()!=null){
            this.category=new CategoryDto(book.getCategory());
        }
    }

    public String getTitle() {
        return title;
    }
    public AuthorDto getAuthor() {
        return author;
    }
    public CategoryDto getCategory() {
        return category;
    }
    public String getIsbn() {
        return isbn;
    }
    public Integer getPages() {
        return pages;
    }
    public Double getPrice() {
        return price;
    }
    public LocalDate getRelease() {
        return release;
    }
    public String getSummary() {
        return summary;
    }
    public Long getId() {
        return id;
    }

}
