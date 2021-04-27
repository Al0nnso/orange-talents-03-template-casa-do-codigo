package br.com.zupacademy.alonso.casadocodigo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.zupacademy.alonso.casadocodigo.controller.form.ClientForm;
import br.com.zupacademy.alonso.casadocodigo.model.Client;

@Controller
public class ClientController {
    
    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/cliente")
    @Transactional
    public ResponseEntity<Client> createClient(@RequestBody @Valid ClientForm form){
        Client client = form.converter(manager);
        manager.persist(client);
        return ResponseEntity.ok(client);
    }
}
