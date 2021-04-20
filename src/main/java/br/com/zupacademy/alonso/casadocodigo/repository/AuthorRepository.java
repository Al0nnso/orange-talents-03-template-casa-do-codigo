package br.com.zupacademy.alonso.casadocodigo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.alonso.casadocodigo.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Long>{
	//Optional<Author> findByEmail(String email);
	boolean existsByEmail(String email);
}
