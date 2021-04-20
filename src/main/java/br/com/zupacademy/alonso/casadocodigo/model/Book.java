package br.com.zupacademy.alonso.casadocodigo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Book {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    private Double price;
    private Integer pages;
    private String isbn;
    private LocalDate release;
    @ManyToOne
    private Author author;
    @ManyToOne
    private Category category;

    @Deprecated
    public Book(){
    }

    public Book(String title, String summary, Double price, Integer pages, String isbn){
        this.title=title;
        this.summary=summary;
        this.price=price;
        this.pages=pages;
        this.isbn=isbn;
        //this.release=release;
        //this.author=author;
        //this.category=category;
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

    public Long getId() {
        return id;
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
    public void setRelease(LocalDate release) {
        this.release = release;
    }
    public String getSummary() {
        return summary;
    }

}
