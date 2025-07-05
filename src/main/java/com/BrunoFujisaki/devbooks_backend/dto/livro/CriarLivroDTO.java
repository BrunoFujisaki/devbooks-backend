package com.BrunoFujisaki.devbooks_backend.dto.livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record CriarLivroDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String autor,
        @NotNull
        UUID categoria,
        @NotBlank
        String descricao,
        @NotNull @PositiveOrZero
        Integer estoque,
        @NotNull @Positive
        BigDecimal valor,
        @NotBlank
        String imagem
) { }
