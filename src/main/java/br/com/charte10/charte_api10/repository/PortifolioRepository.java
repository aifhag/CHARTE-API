package br.com.charte10.charte_api10.repository;

import br.com.charte10.charte_api10.entity.Portifolio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortifolioRepository extends JpaRepository<Portifolio, Integer> {

    List<Portifolio> findByCategoriaId(Integer idCategoria);

    List<Portifolio> findByArtistaId(Integer idArtista);

}