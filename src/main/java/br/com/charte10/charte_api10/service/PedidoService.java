package br.com.charte10.charte_api10.service;

import br.com.charte10.charte_api10.dto.request.PedidoRequestDTO;
import br.com.charte10.charte_api10.entity.Arte;
import br.com.charte10.charte_api10.entity.ItemPedido;
import br.com.charte10.charte_api10.entity.Pedido;
import br.com.charte10.charte_api10.entity.Usuario;
import br.com.charte10.charte_api10.enums.StatusPedido;
import br.com.charte10.charte_api10.exception.RecursoNaoEncontradoException;
import br.com.charte10.charte_api10.repository.ArteRepository;
import br.com.charte10.charte_api10.repository.PedidoRepository;
import br.com.charte10.charte_api10.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArteRepository arteRepository;

    public PedidoService(
            PedidoRepository pedidoRepository,
            UsuarioRepository usuarioRepository,
            ArteRepository arteRepository) {

        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.arteRepository = arteRepository;
    }

    @Transactional
    public Pedido criar(PedidoRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdCliente())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Usuário não encontrado."
                        )
                );

        Pedido pedido = new Pedido();

        pedido.setUsuario(usuario);
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setDataPedido(LocalDateTime.now());

        BigDecimal total = BigDecimal.ZERO;
        for (var itemDto : dto.getItens()) {
            Arte arte = arteRepository.findById(itemDto.getIdArte())
                    .orElseThrow(() -> new RecursoNaoEncontradoException(
                            "Arte não encontrada: " + itemDto.getIdArte()
                    ));

            if (!arte.getDisponivel()) {
                throw new IllegalArgumentException(
                        "A arte selecionada não está disponível: " + arte.getIdArte()
                );
            }

            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setArte(arte);
            item.setQuantidade(itemDto.getQuantidade());
            item.setPrecoUnitario(arte.getPreco());
            total = total.add(item.calcularSubtotal());
            pedido.getItens().add(item);
        }

        pedido.setValorTotal(total);

        return pedidoRepository.save(pedido);
    }

    public Pedido buscarPorId(Integer id) {

        return pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Pedido não encontrado."
                        )
                );
    }

    public Pedido cancelar(Integer id) {

        Pedido pedido = buscarPorId(id);

        if (pedido.getStatus() == StatusPedido.FINALIZADO) {

            throw new RuntimeException(
                    "Não é possível cancelar um pedido finalizado."
            );
        }

        pedido.setStatus(StatusPedido.CANCELADO);

        return pedidoRepository.save(pedido);
    }
    public Pedido alterarStatus(Integer id, StatusPedido novoStatus) {

        Pedido pedido = buscarPorId(id);

        if (pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new RuntimeException(
                    "Não é possível alterar o status de um pedido cancelado."
            );
        }

        if (pedido.getStatus() == StatusPedido.FINALIZADO) {
            throw new RuntimeException(
                    "Não é possível alterar o status de um pedido finalizado."
            );
        }

        pedido.setStatus(novoStatus);

        return pedidoRepository.save(pedido);
    }
}
