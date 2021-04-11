package br.com.zupacademy.alonso.casadocodigo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.alonso.casadocodigo.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer>{
    boolean existsByNome(String nome);
}
