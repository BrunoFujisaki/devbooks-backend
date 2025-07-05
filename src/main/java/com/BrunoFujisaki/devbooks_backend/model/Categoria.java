package com.BrunoFujisaki.devbooks_backend.model;

import com.BrunoFujisaki.devbooks_backend.dto.categoria.AtualizarCategoriaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "categorias")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Categoria {
    @Id
    @UuidGenerator
    private UUID id;
    @NotBlank
    @Column(unique = true)
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public void atualizar(AtualizarCategoriaDTO dto) {
        if (!dto.nome().isBlank()) {
            this.nome = dto.nome();
        }
    }
}
