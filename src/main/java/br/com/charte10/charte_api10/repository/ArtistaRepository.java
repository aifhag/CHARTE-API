package br.com.charte10.charte_api10.repository;

import br.com.charte10.charte_api10.entity.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    Optional<Artista> findByUsuario_IdUsuario(Integer idUsuario);

    boolean existsByUsuario_IdUsuario(Integer idUsuario);

}
