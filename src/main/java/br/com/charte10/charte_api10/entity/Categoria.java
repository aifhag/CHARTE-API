package br.com.charte10.charte_api10.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "categoria",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nome")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;
}