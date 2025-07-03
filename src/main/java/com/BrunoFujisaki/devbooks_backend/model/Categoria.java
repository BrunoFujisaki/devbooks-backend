package com.BrunoFujisaki.devbooks_backend.model;

import com.BrunoFujisaki.devbooks_backend.dto.CategoriaDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categorias")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(unique = true)
    private String nome;
    private Integer quantidadeLivros;

    public Categoria(String nome) {
        this.nome = nome;
        this.quantidadeLivros = 0;
    }

    public void atualizar(CategoriaDTO dto) {
        if (!dto.nome().isBlank()) {
            this.nome = dto.nome();
        }
    }
}
