package br.com.zupacademy.alonso.casadocodigo.controller.form;


import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;

import br.com.zupacademy.alonso.casadocodigo.model.Author;
import br.com.zupacademy.alonso.casadocodigo.model.Book;
import br.com.zupacademy.alonso.casadocodigo.model.Category;

public class BookForm {

    @NotBlank
    private String title;
    @NotBlank @Length(max = 500)
    private String summary;
    @NonNull @Min(value = 20)
    private Double price;
    @NonNull @Min(value = 100)
    private Integer pages;
    @NonNull
    private Double isbn;
    @NonNull @Future
    private LocalDateTime release;
    @NonNull
    private Category category;
    @NonNull
    private Author author;
    
    @Deprecated
    public BookForm(){
    }

    public BookForm(String title, String summary, Double price, Integer pages, Double isbn){
        this.title=title;
        this.summary=summary;
        this.price=price;
        this.pages=pages;
        this.isbn=isbn;
    }

    public String getTitle() {
        return title;
    }
    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Double getIsbn() {
        return isbn;
    }
    public Integer getPages() {
        return pages;
    }
    public Double getPrice() {
        return price;
    }
    public LocalDateTime getRelease() {
        return release;
    }
    public void setRelease(LocalDateTime release) {
        this.release = release;
    }
    public String getSummary() {
        return summary;
    }

    public Book converter(){
        Book book = new Book(this.title,this.summary,this.price,this.pages,this.isbn);
        
        if(this.author != null){
            book.setAuthor(this.author);
        }
        if(this.category != null){
            book.setCategory(this.category);
        }
        if(this.release != null){
            book.setRelease(this.release);
        }

        return book;
    }

}
