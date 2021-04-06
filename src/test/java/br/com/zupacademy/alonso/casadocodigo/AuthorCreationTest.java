package br.com.zupacademy.alonso.casadocodigo;

import org.junit.jupiter.api.Test;

import br.com.zupacademy.alonso.casadocodigo.controller.form.AuthorForm;
import br.com.zupacademy.alonso.casadocodigo.model.Author;

public class AuthorCreationTest {

	@Test
	public void emailsDuplicadosInvalidos() {
		
		AuthorForm author = new AuthorForm();
		author.setNome("Alan");
		author.setEmail("aaaaaa@gmail.com");
		
		
	}
}
