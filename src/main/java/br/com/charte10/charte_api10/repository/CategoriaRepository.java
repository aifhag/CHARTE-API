package br.com.charte10.charte_api10.repository;

import br.com.charte10.charte_api10.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository
        extends JpaRepository<Categoria, Integer> {

    boolean existsByNome(String nome);

    Optional<Categoria> findByNome(String nome);
}