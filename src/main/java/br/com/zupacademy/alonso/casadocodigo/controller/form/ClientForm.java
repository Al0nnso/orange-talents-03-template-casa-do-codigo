package br.com.zupacademy.alonso.casadocodigo.controller.form;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.zupacademy.alonso.casadocodigo.model.Client;
import br.com.zupacademy.alonso.casadocodigo.model.Country;
import br.com.zupacademy.alonso.casadocodigo.model.State;

public class ClientForm {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank @Email
    private String email;
    @NotBlank
    private String document;
    @NotBlank
    private String address;
    private String complement;
    @NotBlank
    private String city;
    private Long stateID;
    @NotNull
    private Long countryID;
    @NotBlank
    private String phone;
    @NotBlank
    private String cep;


    public ClientForm(String name, String surname, String email, String document, String address, String city, Long countryID, String phone, String cep) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.document = document;
        this.address = address;
        this.city = city;
        this.countryID = countryID;
        this.phone = phone;
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getCep() {
        return cep;
    }
    public String getCity() {
        return city;
    }
    public String getComplement() {
        return complement;
    }
    public Long getCountryID() {
        return countryID;
    }
    public String getDocument() {
        return document;
    }
    public String getPhone() {
        return phone;
    }
    public Long getStateID() {
        return stateID;
    }
    public String getSurname() {
        return surname;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void setStateID(Long stateID) {
        this.stateID = stateID;
    }
    @Transactional
    public Client converter(EntityManager manager){
        System.out.println(this.countryID);
        Country country = manager.find(Country.class, this.countryID);
        Assert.state(country!=null,"O ID da country é nulo: "+this.countryID);

        Client client = new Client(this.name,this.surname,this.email,this.document,this.address,this.city,country,this.phone,this.cep);

        if(this.stateID!=null){
            State state = manager.find(State.class, this.stateID);
            Assert.state(state!=null,"O ID do state é nulo: "+this.stateID);
            client.setState(state);
        }

        client.setComplement(complement);
        
        return client;
    }

}
