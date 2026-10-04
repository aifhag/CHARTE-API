package br.com.charte10.charte_api10.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "portifolio")
@Data
public class Portifolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_portifolio")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_artista")
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    private String titulo;

    private BigDecimal preco;

    private String descricao;

    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;

    private String imagem;

    @Column(name = "status_venda")
    private String statusVenda;

}