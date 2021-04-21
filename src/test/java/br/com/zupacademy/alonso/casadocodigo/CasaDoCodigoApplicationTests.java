package br.com.zupacademy.alonso.casadocodigo;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;
import org.springframework.web.util.NestedServletException;

import br.com.zupacademy.alonso.casadocodigo.controller.AuthorController;
import br.com.zupacademy.alonso.casadocodigo.controller.dto.AuthorDto;
import br.com.zupacademy.alonso.casadocodigo.controller.form.AuthorForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.BookForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.CategoryForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.CountryForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.PaisForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.StateForm;
import br.com.zupacademy.alonso.casadocodigo.model.Author;
import br.com.zupacademy.alonso.casadocodigo.repository.AuthorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class CasaDoCodigoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

    @Autowired
    ObjectMapper jsonMapper;

	@Test
	public void criaUsuarioTest() throws Exception {

		URI uri = new URI("/autor");

		testStatusCode(uri, new AuthorForm("Teste","teste@teste.com","teste teste"),200);// Author creation
		testStatusCode(uri, new AuthorForm("Teste","teste@teste.com","teste teste"),400);// Duplicated author
		testStatusCode(uri, new AuthorForm("","",""),400);// Empty author
		testStatusCode(uri, new AuthorForm("null","null","null"),400);// null author
		testStatusCode(uri, new AuthorForm("Teste2","teste2@teste.com",testMaxString(500)),400);// max description length
	}

	@Test
	public void criaCategoriaTest() throws Exception {

		URI uri = new URI("/categoria");
		
		testStatusCode(uri, new CategoryForm("Test"), 200);// Category creation
		testStatusCode(uri, new CategoryForm("Test"), 400);// Duplicated category
		testStatusCode(uri, new CategoryForm(""), 400);// Empty category
		//testStatusCode(uri, new CategoryForm("null"), 400);// null category
	}
	@Test
	public void criaLivroTest() throws Exception{

		URI uri = new URI("/livro");

		testStatusCode(uri, new BookForm("Title", "a summmary", 234.90, 567, "SOME-ISBN"), 200);// Book creation
		testStatusCode(uri, new BookForm("Title", "a summmary", 234.90, 567, "SOME-ISBN"), 200);// Duplicated book (is possible)
		testStatusCode(uri, new BookForm("Title", "a summmary", 234.90, 10, "SOME-ISBN"), 400);// Min page number
		testStatusCode(uri, new BookForm("Title", "a summmary", 5.90, 567, "SOME-ISBN"), 400);// Min price
		testStatusCode(uri, new BookForm("", "", 234.90, 567, ""), 400);// Empty book
		//testStatusCode(uri, new BookForm("null", "null", 234.90, 567, "null"), 400);// Null book
	}

	@Test
	public void criaPaisTest() throws Exception{

		URI uri = new URI("/pais");

		CountryForm pais = new CountryForm("Brasil");
		StateForm estado = new StateForm("Minas Gerais",Long.valueOf(1));

		testStatusCode(uri, pais, 200);// Pais creation
		testStatusCode(uri, estado, 200);// Pais creation

	}

	//@Test
	public void bookIdInexistenteTest() throws Exception{

		URI uri = new URI("/livro");

		BookForm form = new BookForm("null", "null", 234.90, 567, "null");
		form.setAuthorID(Long.valueOf(1));
		// Inexistent author ID
		//MockMvcRequestBuilders.post(uri).content(json(form)).contentType(MediaType.APPLICATION_JSON);
		//Exception exception = Assert.assertThrows(NestedServletException.class, 
		//()-> MockMvcRequestBuilders.post(uri).content(json(form)).contentType(MediaType.APPLICATION_JSON));
		//assertTrue(exception.getMessage().contains("not found"));

		// Existent author ID
		MockMvcRequestBuilders.post(new URI("/autor")).content(json(new AuthorForm("Teste","teste@teste.com","teste teste"))).contentType(MediaType.APPLICATION_JSON);
		testStatusCode(uri, form, 200);// Existent author ID

		form.setCategoryID(Long.valueOf(1));
		// Inexistent category ID
		//Assert.assertThrows(IllegalStateException.class, 
		//()-> MockMvcRequestBuilders.post(uri).content(json(form)).contentType(MediaType.APPLICATION_JSON));
		
		MockMvcRequestBuilders.post(new URI("/categoria")).content(json(new CategoryForm("Teste"))).contentType(MediaType.APPLICATION_JSON);
		testStatusCode(uri, form, 200);// Existent category ID
	}
	
	public void testStatusCode(URI uri,Object form,Integer code) throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json(form))
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(code));
	}

	public String testMaxString(int max){
		String result="a";
		for(int i=0;i<max;i++){
			result = new String(result+"a");
		}
		return result;
	}

	String json(Object request) throws JsonProcessingException{
        return jsonMapper.writeValueAsString(request);
    }
}
