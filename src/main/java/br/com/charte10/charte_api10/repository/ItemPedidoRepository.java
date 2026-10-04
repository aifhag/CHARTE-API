package br.com.charte10.charte_api10.repository;

import br.com.charte10.charte_api10.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoRepository
        extends JpaRepository<ItemPedido, Integer> {

    List<ItemPedido> findByPedidoIdPedido(Integer idPedido);

    boolean existsByArteIdArte(Integer idArte);
}