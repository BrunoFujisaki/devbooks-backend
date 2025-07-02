package com.BrunoFujisaki.devbooks_backend.dto;

import com.BrunoFujisaki.devbooks_backend.model.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record LivroDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String autor,
        @NotBlank
        String categoria,
        @NotBlank
        String descricao,
        @NotNull @PositiveOrZero
        Integer quantidade,
        @NotNull @Positive
        BigDecimal valor,
        @NotBlank
        String imagem
) {
    public LivroDTO(Livro livro) {
        this(
            livro.getTitulo(),
            livro.getAutor(),
            livro.getCategoria().getNome(),
            livro.getDescricao(),
            livro.getQuantidade(),
            livro.getValor(),
            livro.getImagem()
        );
    }
}
