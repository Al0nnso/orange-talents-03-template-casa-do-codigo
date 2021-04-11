package br.com.zupacademy.alonso.casadocodigo.controller.form;


import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import br.com.zupacademy.alonso.casadocodigo.model.Author;
import br.com.zupacademy.alonso.casadocodigo.model.Book;
import br.com.zupacademy.alonso.casadocodigo.model.Category;

public class BookForm {

    @NotBlank
    private String title;
    @NotBlank @Length(max = 500)
    private String summary;
    @NotBlank
    @Length(min = 20)
    private Float price;
    @Length(min = 100)
    private Integer pages;
    @NotBlank
    private Double isbn;
    @Future
    private LocalDateTime release;
    @NonNull
    private Category category;
    @NonNull
    private Author author;

    public BookForm(String title, String summary, Float price, Integer pages, Double isbn){
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
    public Float getPrice() {
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
        book.setAuthor(this.author);
        book.setCategory(this.category);
        book.setRelease(this.release);
        return book;
    }

}
