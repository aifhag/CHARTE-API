package br.com.charte10.charte_api10.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class PedidoRequestDTO {

    @NotNull(message = "O cliente é obrigatório.")
    private Integer idCliente;

    @NotEmpty(message = "O pedido precisa possuir pelo menos uma obra.")
    private List<ItemPedidoRequestDTO> itens;

    public PedidoRequestDTO() {
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public List<ItemPedidoRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoRequestDTO> itens) {
        this.itens = itens;
    }
}