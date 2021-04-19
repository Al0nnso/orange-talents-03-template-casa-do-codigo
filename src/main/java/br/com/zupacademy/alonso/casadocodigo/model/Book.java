package br.com.zupacademy.alonso.casadocodigo.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Book {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String summary;
    private Double price;
    private Integer pages;
    private Double isbn;
    private LocalDateTime release;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Author author;

    @Deprecated
    public Book(){
    }

    public Book(String title, String summary, Double price, Integer pages, Double isbn){
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

    public Integer getId() {
        return id;
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

}