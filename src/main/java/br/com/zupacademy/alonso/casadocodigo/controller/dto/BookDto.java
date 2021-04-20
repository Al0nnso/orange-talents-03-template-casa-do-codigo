package br.com.zupacademy.alonso.casadocodigo.controller.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.zupacademy.alonso.casadocodigo.model.Author;
import br.com.zupacademy.alonso.casadocodigo.model.Book;
import br.com.zupacademy.alonso.casadocodigo.model.Category;

public class BookDto {

    private String title;
    private String summary;
    private Double price;
    private Integer pages;
    private String isbn;
    private LocalDate release;
    private AuthorDto author;
    private CategoryDto category;
    
    public BookDto(Book book){
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

}
