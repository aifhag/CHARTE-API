package br.com.charte10.charte_api10.dto;

import br.com.charte10.charte_api10.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;

public class AlterarStatusPedidoDTO {

    @NotNull(message = "O status é obrigatório.")
    private StatusPedido status;

    public AlterarStatusPedidoDTO() {
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }
}