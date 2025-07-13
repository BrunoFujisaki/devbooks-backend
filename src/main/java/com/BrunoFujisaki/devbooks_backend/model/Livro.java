package com.BrunoFujisaki.devbooks_backend.model;

import com.BrunoFujisaki.devbooks_backend.dto.livro.AtualizarLivroDTO;
import com.BrunoFujisaki.devbooks_backend.dto.livro.CriarLivroDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "livros")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Livro {
    @Id
    @UuidGenerator
    private UUID id;
    @NotBlank
    @Column(unique = true)
    private String titulo;
    @NotBlank
    private String autor; //Criar classe autor!!
    @NotBlank
    private String descricao;
    @NotNull
    @PositiveOrZero
    private Integer estoque;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotBlank
    private String imagem;
    @ManyToOne
    private Categoria categoria;

    public Livro(CriarLivroDTO dto, Categoria categoria) {
        this.titulo = dto.titulo();
        this.autor = dto.autor();
        this.descricao = dto.descricao();
        this.estoque = dto.estoque();
        this.valor = dto.valor();
        this.imagem = dto.imagem();
        this.categoria = categoria;
    }

    public void atualizar(AtualizarLivroDTO dto, Categoria categoria) {
        if (dto.titulo() != null && !dto.titulo().isBlank()) {
            this.titulo = dto.titulo();
        }
        if (dto.autor() != null && !dto.autor().isBlank()) {
            this.autor = dto.autor();
        }
        if (dto.descricao() != null && !dto.descricao().isBlank()) {
            this.descricao = dto.descricao();
        }
        if (dto.descricao() != null && dto.estoque() != null) {
            this.estoque = dto.estoque();
        }
        if (dto.valor() != null) {
            this.valor = dto.valor();
        }
        if (dto.imagem() != null && !dto.imagem().isBlank()) {
            this.imagem = dto.imagem();
        }
        if (categoria != null) {
            this.categoria = categoria;
        }
    }

    public void atualizarQuantidade(Integer quantidade, Boolean adicionar) {
        if (adicionar) {
            this.estoque += quantidade;
        } else {
            this.estoque -= quantidade;
        }
    }
}
