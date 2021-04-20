package br.com.zupacademy.alonso.casadocodigo.controller.dto;

import br.com.zupacademy.alonso.casadocodigo.model.Book;

public class BookListDto {
    private Long id;
    private String title;
    
    public BookListDto(Book book){
        this.id=book.getId();
        this.title=book.getTitle();
    }

    public String getTitle() {
        return title;
    }
    public Long getId() {
        return id;
    }

}
