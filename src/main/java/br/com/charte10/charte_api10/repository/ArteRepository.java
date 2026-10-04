package br.com.charte10.charte_api10.repository;

import br.com.charte10.charte_api10.entity.Arte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArteRepository
        extends JpaRepository<Arte, Integer> {

    List<Arte> findByArtistaIdArtista(Integer idArtista);

    List<Arte> findByCategoriaIdCategoria(Integer idCategoria);

    List<Arte> findByDisponivelTrue();
}
