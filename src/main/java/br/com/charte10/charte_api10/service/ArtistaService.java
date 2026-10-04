package br.com.charte10.charte_api10.service;

import br.com.charte10.charte_api10.dto.request.ArtistaRequestDTO;
import br.com.charte10.charte_api10.dto.response.ArtistaResponseDTO;
import br.com.charte10.charte_api10.entity.Artista;
import br.com.charte10.charte_api10.entity.Usuario;
import br.com.charte10.charte_api10.enums.TipoUsuario;
import br.com.charte10.charte_api10.exception.RecursoNaoEncontradoException;
import br.com.charte10.charte_api10.repository.ArtistaRepository;
import br.com.charte10.charte_api10.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistaService {

    private final ArtistaRepository artistaRepository;
    private final UsuarioRepository usuarioRepository;

    public ArtistaResponseDTO cadastrar(
            ArtistaRequestDTO dto) {

        Usuario usuario = usuarioRepository
                .findById(dto.getIdUsuario())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Usuário não encontrado."
                        ));

        if (artistaRepository
                .existsByUsuario_IdUsuario(usuario.getIdUsuario())) {

            throw new RuntimeException(
                    "Este usuário já possui perfil de artista."
            );
        }

        Artista artista = new Artista();

        artista.setUsuario(usuario);
        artista.setNomeArtistico(dto.getNomeArtistico());
        artista.setBiografia(dto.getBiografia());

        usuario.setTipoUsuario(TipoUsuario.Artista);

        usuarioRepository.save(usuario);

        Artista salvo = artistaRepository.save(artista);

        return converter(salvo);
    }

    public List<ArtistaResponseDTO> listar() {

        return artistaRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public ArtistaResponseDTO buscar(Integer id) {

        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Artista não encontrado."
                        ));

        return converter(artista);
    }

    public void excluir(Integer id) {

        Artista artista = buscarEntity(id);

        Usuario usuario = artista.getUsuario();

        usuario.setTipoUsuario(TipoUsuario.Cliente);

        usuarioRepository.save(usuario);

        artistaRepository.delete(artista);
    }

    private Artista buscarEntity(Integer id) {

        return artistaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Artista não encontrado."
                        ));
    }

    private ArtistaResponseDTO converter(
            Artista artista) {

        ArtistaResponseDTO dto =
                new ArtistaResponseDTO();

        dto.setIdArtista(
                artista.getIdArtista()
        );

        dto.setIdUsuario(
                artista.getUsuario().getIdUsuario()
        );

        dto.setNomeArtistico(
                artista.getNomeArtistico()
        );

        dto.setBiografia(
                artista.getBiografia()
        );

        return dto;
    }
}
