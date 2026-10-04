package br.com.charte10.charte_api10.dto.usuario;

import br.com.charte10.charte_api10.enums.Genero;
import br.com.charte10.charte_api10.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioCadastroDTO {

        @NotBlank(message = "Nome de usuário é obrigatório")
        private String nomeUsuario;

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        private String email;

        private LocalDate dataNascimento;

        private Genero genero;

        private String telefone;

        private TipoUsuario tipoUsuario;
}