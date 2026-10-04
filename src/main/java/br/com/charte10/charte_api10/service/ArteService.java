package br.com.charte10.charte_api10.service;

import br.com.charte10.charte_api10.dto.request.ArteRequestDTO;
import br.com.charte10.charte_api10.entity.Arte;
import br.com.charte10.charte_api10.entity.Artista;
import br.com.charte10.charte_api10.entity.Categoria;
import br.com.charte10.charte_api10.exception.RecursoNaoEncontradoException;
import br.com.charte10.charte_api10.repository.ArteRepository;
import br.com.charte10.charte_api10.repository.ArtistaRepository;
import br.com.charte10.charte_api10.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArteService {

    private final ArteRepository arteRepository;
    private final ArtistaRepository artistaRepository;
    private final CategoriaRepository categoriaRepository;

    public ArteService(
            ArteRepository arteRepository,
            ArtistaRepository artistaRepository,
            CategoriaRepository categoriaRepository) {

        this.arteRepository = arteRepository;
        this.artistaRepository = artistaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Arte cadastrar(ArteRequestDTO dto) {

        Artista artista = artistaRepository
                .findById(dto.getIdArtista())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Artista não encontrado."
                        )
                );

        Categoria categoria = categoriaRepository
                .findById(dto.getIdCategoria())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Categoria não encontrada."
                        )
                );

        Arte arte = new Arte();

        arte.setArtista(artista);
        arte.setCategoria(categoria);
        arte.setTitulo(dto.getTitulo());
        arte.setDescricao(dto.getDescricao());
        arte.setPreco(dto.getPreco());
        arte.setDisponivel(true);

        return arteRepository.save(arte);
    }

    public List<Arte> listarTodas() {

        return arteRepository.findAll();
    }

    public List<Arte> listarDisponiveis() {

        return arteRepository.findByDisponivelTrue();
    }

    public List<Arte> listarPorArtista(Integer idArtista) {

        return arteRepository.findByArtistaIdArtista(idArtista);
    }

    public List<Arte> listarPorCategoria(Integer idCategoria) {

        return arteRepository.findByCategoriaIdCategoria(idCategoria);
    }

    public Arte buscarPorId(Integer id) {

        return arteRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Arte não encontrada."
                        )
                );
    }

    public Arte atualizar(
            Integer id,
            ArteRequestDTO dto) {

        Arte arte = buscarPorId(id);

        Artista artista = artistaRepository
                .findById(dto.getIdArtista())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Artista não encontrado."
                        )
                );

        Categoria categoria = categoriaRepository
                .findById(dto.getIdCategoria())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Categoria não encontrada."
                        )
                );

        if (!arte.getArtista()
                .getIdArtista()
                .equals(artista.getIdArtista())) {

            throw new RuntimeException(
                    "O artista só pode alterar suas próprias obras."
            );
        }

        arte.setCategoria(categoria);
        arte.setTitulo(dto.getTitulo());
        arte.setDescricao(dto.getDescricao());
        arte.setPreco(dto.getPreco());

        return arteRepository.save(arte);
    }

    public Arte alterarDisponibilidade(
            Integer id,
            Boolean disponivel) {

        Arte arte = buscarPorId(id);

        arte.setDisponivel(disponivel);

        return arteRepository.save(arte);
    }

    public void excluir(
            Integer id,
            Integer idArtista) {

        Arte arte = buscarPorId(id);

        if (!arte.getArtista()
                .getIdArtista()
                .equals(idArtista)) {

            throw new RuntimeException(
                    "O artista só pode excluir suas próprias obras."
            );
        }

        arteRepository.delete(arte);
    }
}
