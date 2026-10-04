package br.com.charte10.charte_api10.dto.response;

import lombok.Data;

@Data
public class ArtistaResponseDTO {

    private Integer idArtista;

    private Integer idUsuario;

    private String nomeArtistico;

    private String biografia;
}