package br.com.charte10.charte_api10.dto.response;

import br.com.charte10.charte_api10.enums.Genero;
import br.com.charte10.charte_api10.enums.StatusConta;
import br.com.charte10.charte_api10.enums.TipoUsuario;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UsuarioResponseDTO {

    private Integer id;

    private String nomeUsuario;

    private String email;

    private LocalDate dataNascimento;

    private Genero genero;

    private String telefone;

    private String fotoPerfil;

    private String bannerPerfil;

    private TipoUsuario tipoUsuario;

    private StatusConta statusConta;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

}
