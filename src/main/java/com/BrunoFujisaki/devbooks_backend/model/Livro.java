package com.BrunoFujisaki.devbooks_backend.model;

import com.BrunoFujisaki.devbooks_backend.dto.LivroDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "livros")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(unique = true)
    private String titulo;
    @NotBlank
    private String autor; //Criar classe autor!!
    @NotBlank
    private String descricao;
    @NotNull
    @PositiveOrZero
    private Integer quantidade;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotBlank
    private String imagem;
    @ManyToOne
    private Categoria categoria;

    public Livro(LivroDTO dto, Categoria categoria) {
        this.titulo = dto.titulo();
        this.autor = dto.autor();
        this.descricao = dto.descricao();
        this.quantidade = dto.quantidade();
        this.valor = dto.valor();
        this.imagem = dto.imagem();
        this.categoria = categoria;
    }
}
