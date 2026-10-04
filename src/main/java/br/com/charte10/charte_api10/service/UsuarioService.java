package br.com.charte10.charte_api10.service;

import br.com.charte10.charte_api10.dto.usuario.UsuarioCadastroDTO;
import br.com.charte10.charte_api10.entity.Usuario;
import br.com.charte10.charte_api10.enums.StatusConta;
import br.com.charte10.charte_api10.enums.TipoUsuario;
import br.com.charte10.charte_api10.exception.RecursoNaoEncontradoException;
import br.com.charte10.charte_api10.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(UsuarioCadastroDTO dto) {

        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado.");
        }

        Usuario usuario = new Usuario();

        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setGenero(dto.getGenero());
        usuario.setTelefone(dto.getTelefone());

        usuario.setTipoUsuario(
                dto.getTipoUsuario() != null
                        ? dto.getTipoUsuario()
                        : TipoUsuario.Cliente
        );

        usuario.setStatusConta(StatusConta.Ativo);

        usuario.setDataCriacao(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Integer id) {

        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Usuário não encontrado."
                        )
                );
    }
}