package br.com.zupacademy.alonso.casadocodigo;




import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.InstanceOf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import br.com.zupacademy.alonso.casadocodigo.controller.AuthorController;
import br.com.zupacademy.alonso.casadocodigo.controller.dto.AuthorDto;
import br.com.zupacademy.alonso.casadocodigo.controller.form.AuthorForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.BookForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.CategoryForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.ClientForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.CountryForm;
import br.com.zupacademy.alonso.casadocodigo.controller.form.StateForm;
import br.com.zupacademy.alonso.casadocodigo.model.Author;
import br.com.zupacademy.alonso.casadocodigo.repository.AuthorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"spring.config.name=myapp-test-h2","myapp.trx.datasource.url=jdbc:h2:mem:trxServiceStatus"})
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
	public void criaClienteTest() throws Exception{

		URI uri = new URI("/cliente");

		ClientForm cliente = new ClientForm("Andres","Alonso","andresalonnso@gmail.com","653242655-00","Rua Fernando Carrvalho 35","Belo Horizonte",Long.valueOf(1),"31720390","31996644783");
		cliente.setStateID(Long.valueOf(1));

		testSpringError(uri, cliente);

		// Create pais e estado
		CountryForm pais = new CountryForm("Bahamas");
		StateForm estado = new StateForm("Naosei",Long.valueOf(1));
		criaObjeto(new URI("/pais"), pais);
		criaObjeto(new URI("/estado"), estado);

		testStatusCode(uri, cliente, 200);
	}

	@Test
	public void criaRegiaoTest() throws Exception{

		CountryForm pais = new CountryForm("Brasil");
		StateForm estado = new StateForm("Minas Gerais",Long.valueOf(1));
		StateForm estadoIDInexistente = new StateForm("Rio de Janeiro",Long.valueOf(2));

		testStatusCode(new URI("/pais"), pais, 200);// Pais creation
		testStatusCode(new URI("/estado"), estado, 200);// Pais creation
		testSpringError(new URI("/estado"), estadoIDInexistente);// Pais creation
	}

	@Test
	public void bookIdInexistenteTest() throws Exception{

		URI uri = new URI("/livro");

		BookForm form = new BookForm("Test", "Test", 234.90, 567, "Test");
		testStatusCode(uri, form, 200);
		form.setAuthorID(Long.valueOf(1));
		// Inexistent author ID
		testSpringError(uri, form);

		// Existent author ID
		criaObjeto(new URI("/autor"),new AuthorForm("TesteForBook","testeforbook@teste.com","teste para o book"));
		testStatusCode(uri, form, 200);

		// Inexistent category ID
		form.setCategoryID(Long.valueOf(1));
		testSpringError(uri, form);

		// Existent category ID
		criaObjeto(new URI("/categoria"),new CategoryForm("Teste"));
		testStatusCode(uri, form, 200);
	}

	public void testSpringError(URI uri,Object form) throws Exception{
		try{
			criaObjeto(uri, form);
		}catch(NestedServletException e){
			Assert.assertEquals(NestedServletException.class, e.getClass());
		}
	}

	public void criaObjeto(URI uri,Object form) throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json(form)).contentType(MediaType.APPLICATION_JSON));
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
