package br.com.zupacademy.alonso.casadocodigo.controller;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.alonso.casadocodigo.controller.dto.CategoryDto;
import br.com.zupacademy.alonso.casadocodigo.controller.form.CategoryForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.ProibeNomeCategoryDuplicadoValidator;
import br.com.zupacademy.alonso.casadocodigo.model.Category;

@RestController
public class CategoryController {

    @Autowired
    private ProibeNomeCategoryDuplicadoValidator proibeNomeCategoryDuplicadoValidator;

    /*private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository){
        this.repository=repository;
    }*/
    @PersistenceContext
    private EntityManager manager;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeNomeCategoryDuplicadoValidator);
    }

    @PostMapping("/categoria")
    @Transactional
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryForm form){
        Category categoria = form.converter();
        //repository.save(categoria);
        manager.persist(categoria);
        CategoryDto response = new CategoryDto(categoria);
        return ResponseEntity.ok(response);
    }
}
