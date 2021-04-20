package br.com.zupacademy.alonso.casadocodigo.controller.form;


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

public class BookForm {

    @NotBlank
    private String title;
    @NotBlank @Length(max = 500)
    private String summary;
    @NonNull @Min(20)
    private Double price;
    @NonNull @Min(100)
    private Integer pages;
    @NonNull
    private String isbn;
    @NonNull @Future //@JsonFormat(pattern = "dd/MM/yyyy",shape = Shape.STRING)
    private LocalDate release;
    @NonNull
    private Long authorID;
    @NonNull
    private Long categoryID;
    
    @Deprecated
    public BookForm(){
    }

    public BookForm(String title, String summary, Double price, Integer pages, String isbn){
        this.title=title;
        this.summary=summary;
        this.price=price;
        this.pages=pages;
        this.isbn=isbn;
        //this.release=release;
        //this.authorID=authorID;
        //this.categoryID=categoryID;
    }

    public String getTitle() {
        return title;
    }
    public Long getAuthorID() {
        return authorID;
    }
    public Long getCategoryID() {
        return categoryID;
    }
    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }
    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
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
    @Transactional
    public Book converter(EntityManager manager){
        //Assert.state(author!=null,"O ID do author é nulo: "+this.authorID);
        //Assert.state(category!=null,"O ID da categoria é nulo: "+this.categoryID);

        Book book = new Book(this.title,this.summary,this.price,this.pages,this.isbn);
        //System.out.println(this.authorID);
        /*if(this.authorID){

        }*/
        
        /*if(form.getAuthorID()!=null && manager.find(Author.class,form.getAuthorID())==null) {
            errors.rejectValue("author", null, "Não existe um author com este id: "+form.getAuthorID());
        }

        if(form.getCategoryID()!=null && manager.find(Category.class,form.getCategoryID())==null) {
            errors.rejectValue("category", null, "Não existe uma category com este id: "+form.getCategoryID());
        }*/

        if(this.authorID!=null){
            Author author = manager.find(Author.class, this.authorID);
            Assert.state(author!=null,"O ID do author é nulo: "+this.authorID);
            book.setAuthor(author);
        }
        if(this.categoryID!=null){
            Category category = manager.find(Category.class, this.categoryID);
            Assert.state(category!=null,"O ID da categoria é nulo: "+this.categoryID);
            book.setCategory(category);
        }

        book.setRelease(this.release);

        return book;
    }

}
