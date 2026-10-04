package br.com.charte10.charte_api10.service;

import br.com.charte10.charte_api10.dto.request.CategoriaRequestDTO;
import br.com.charte10.charte_api10.entity.Categoria;
import br.com.charte10.charte_api10.exception.RecursoNaoEncontradoException;
import br.com.charte10.charte_api10.repository.ArteRepository;
import br.com.charte10.charte_api10.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ArteRepository arteRepository;

    public CategoriaService(
            CategoriaRepository categoriaRepository,
            ArteRepository arteRepository) {

        this.categoriaRepository = categoriaRepository;
        this.arteRepository = arteRepository;
    }

    public Categoria cadastrar(CategoriaRequestDTO dto) {

        if (categoriaRepository.existsByNome(dto.getNome())) {

            throw new RuntimeException(
                    "Já existe uma categoria com esse nome."
            );
        }

        Categoria categoria = new Categoria();

        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarTodas() {

        return categoriaRepository.findAll();
    }

    public Categoria buscarPorId(Integer id) {

        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException(
                                "Categoria não encontrada."
                        )
                );
    }

    public Categoria atualizar(
            Integer id,
            CategoriaRequestDTO dto) {

        Categoria categoria = buscarPorId(id);

        if (!categoria.getNome().equals(dto.getNome())
                && categoriaRepository.existsByNome(dto.getNome())) {

            throw new RuntimeException(
                    "Já existe uma categoria com esse nome."
            );
        }

        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        return categoriaRepository.save(categoria);
    }

    public void excluir(Integer id) {

        Categoria categoria = buscarPorId(id);

        List<br.com.charte10.charte_api10.entity.Arte> artes =
                arteRepository.findByCategoriaIdCategoria(id);

        if (!artes.isEmpty()) {

            throw new RuntimeException(
                    "Não é possível excluir uma categoria que possui obras."
            );
        }

        categoriaRepository.delete(categoria);
    }
}