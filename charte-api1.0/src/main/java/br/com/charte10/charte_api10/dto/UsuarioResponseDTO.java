package br.com.charte10.charte_api10.dto;

import br.com.charte10.charte_api10.enums.StatusConta;
import br.com.charte10.charte_api10.enums.TipoUsuario;
import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private Integer idUsuario;

    private String nomeUsuario;

    private String email;

    private TipoUsuario tipoUsuario;

    private StatusConta statusConta;

}