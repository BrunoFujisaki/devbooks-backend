package com.BrunoFujisaki.devbooks_backend.model;

import com.BrunoFujisaki.devbooks_backend.dto.AtualizarCategoriaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @Setter
    private Integer quantidadeLivros;

    public Categoria(String nome) {
        this.nome = nome;
        this.quantidadeLivros = 0;
    }

    public void atualizar(AtualizarCategoriaDTO dto) {
        if (!dto.nome().isBlank()) {
            this.nome = dto.nome();
        }
    }
}
