package br.com.zupacademy.alonso.casadocodigo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.alonso.casadocodigo.controller.form.CountryForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.StateForm;
import br.com.zupacademy.alonso.casadocodigo.model.Country;
import br.com.zupacademy.alonso.casadocodigo.model.State;

@RestController
public class RegionController {
    
    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/pais")
    @Transactional
    public ResponseEntity<Country> createCountry(@RequestBody @Valid CountryForm form){
        Country country = form.converter();
        manager.persist(country);
        return ResponseEntity.ok(country);
    }

    @PostMapping("/estado")
    @Transactional
    public ResponseEntity<State> createState(@RequestBody @Valid StateForm form){
        State state = form.converter(manager);
        manager.persist(state);
        return ResponseEntity.ok(state);
    }
}
