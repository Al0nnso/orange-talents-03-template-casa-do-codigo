package br.com.zupacademy.alonso.casadocodigo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.zupacademy.alonso.casadocodigo.model.Author;

@SpringBootTest
class CasaDoCodigoApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void addAuthorTest() {
		Author author = new Author();
		author.setNome("AFREDO");
		author.setDescricao("nao sei");
		author.setEmail("alfredo@gmail.com");
		//createAuthor()
	}

}
