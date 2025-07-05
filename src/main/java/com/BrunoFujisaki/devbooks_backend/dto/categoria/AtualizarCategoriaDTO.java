package com.BrunoFujisaki.devbooks_backend.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AtualizarCategoriaDTO(
        @NotNull
        UUID id,
        @NotBlank
        String nome
) {
}
