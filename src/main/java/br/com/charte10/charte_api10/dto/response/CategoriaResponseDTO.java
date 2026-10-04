package br.com.charte10.charte_api10.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoriaResponseDTO {

    private Integer idCategoria;
    private String nome;
    private String descricao;
}