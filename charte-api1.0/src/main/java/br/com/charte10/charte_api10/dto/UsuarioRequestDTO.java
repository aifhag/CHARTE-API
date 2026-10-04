
package br.com.charte10.charte_api10.dto;

import br.com.charte10.charte_api10.enums.Genero;
import br.com.charte10.charte_api10.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequestDTO {

    @NotBlank
    private String nomeUsuario;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String senhaHash;

    private LocalDate dataNascimento;

    private Genero genero;

    private String telefone;

    private TipoUsuario tipoUsuario;

}