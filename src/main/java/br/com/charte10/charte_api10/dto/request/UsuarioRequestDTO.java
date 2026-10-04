package br.com.charte10.charte_api10.dto.request;

import br.com.charte10.charte_api10.enums.Genero;
import br.com.charte10.charte_api10.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "Nome de usuário é obrigatório")
    private String nomeUsuario;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    private LocalDate dataNascimento;

    private Genero genero;

    private String telefone;

    private TipoUsuario tipoUsuario;
}