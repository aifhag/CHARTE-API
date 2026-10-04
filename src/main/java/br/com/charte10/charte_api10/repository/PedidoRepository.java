package br.com.charte10.charte_api10.repository;

import br.com.charte10.charte_api10.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository
        extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByClienteId(Integer idCliente);
}