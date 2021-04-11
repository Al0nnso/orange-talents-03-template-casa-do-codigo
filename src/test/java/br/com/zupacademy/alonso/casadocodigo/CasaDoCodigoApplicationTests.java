package br.com.zupacademy.alonso.casadocodigo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.zupacademy.alonso.casadocodigo.controller.AuthorController;
import br.com.zupacademy.alonso.casadocodigo.controller.dto.AuthorDto;
import br.com.zupacademy.alonso.casadocodigo.controller.form.AuthorForm;
import br.com.zupacademy.alonso.casadocodigo.model.Author;
import br.com.zupacademy.alonso.casadocodigo.repository.AuthorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CasaDoCodigoApplicationTests {

	private String nome="Clebin";
	private String email="aaclebin@gmail.com";
	private String descricao = "clebin do vapo";
	private AuthorForm form = new AuthorForm(this.nome,this.email,this.descricao);

	@Autowired
	AuthorRepository repository;

	@Test
	public void criarUsuarioTest() {
		AuthorController controller = new AuthorController(repository);
		AuthorDto response = controller.createAuthor(form).getBody();
		assertEquals(response.getNome(),this.nome);
		assertEquals(response.getEmail(),this.email);
		assertEquals(response.getDescricao(),this.descricao);
	}

	@Test
	public void emailsDuplicadosInvalidos() {
		AuthorController controller = new AuthorController(repository);
		AuthorDto response = controller.createAuthor(form).getBody();
		comparaAuthors(form.converter(),response);
		System.out.println("adsadsa");
		AuthorDto responseForAllEquals = controller.createAuthor(form).getBody();
		comparaAuthors(form.converter(),responseForAllEquals);

		AuthorForm form2 = new AuthorForm("anotherthing",this.email,"anotherthing");
		ResponseEntity responseEmailEquals = controller.createAuthor(form2);
		assertEquals(HttpStatus.ACCEPTED,responseEmailEquals.getStatusCode());
		comparaAuthors(form2.converter(),(AuthorDto)responseEmailEquals.getBody());
		
	}

	private void comparaAuthors(Author author,AuthorDto response){
		assertEquals(response.getNome(),author.getNome());
		assertEquals(response.getEmail(),author.getEmail());
		assertEquals(response.getDescricao(),author.getDescricao());
	}
}
