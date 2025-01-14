package br.com.zupacademy.alonso.casadocodigo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.alonso.casadocodigo.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book,Long>{
	boolean existsById(Long id);
}
