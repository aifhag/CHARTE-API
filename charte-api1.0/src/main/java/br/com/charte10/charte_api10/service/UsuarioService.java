package br.com.charte10.charte_api10.service;

import br.com.charte10.charte_api10.dto.UsuarioRequestDTO;
import br.com.charte10.charte_api10.entity.Usuario;
import br.com.charte10.charte_api10.enums.StatusConta;
import br.com.charte10.charte_api10.exception.RecursoNaoEncontradoException;
import br.com.charte10.charte_api10.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario cadastrar(UsuarioRequestDTO dto){

        if(repository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email já cadastrado.");
        }

        Usuario usuario = new Usuario();

        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setSenhaHash(dto.getSenhaHash());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setGenero(dto.getGenero());
        usuario.setTelefone(dto.getTelefone());
        usuario.setTipoUsuario(dto.getTipoUsuario());

        usuario.setStatusConta(StatusConta.ATIVO);
        usuario.setDataCriacao(LocalDateTime.now());

        return repository.save(usuario);

    }

    public List<Usuario> listarTodos(){
        return repository.findAll();
    }

    public Usuario buscarPorId(Integer id){

        return repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Usuário não encontrado."));
    }

    public void excluir(Integer id){

        Usuario usuario = buscarPorId(id);

        repository.delete(usuario);

    }

}