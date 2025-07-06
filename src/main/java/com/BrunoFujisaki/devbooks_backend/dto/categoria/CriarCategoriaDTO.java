package com.BrunoFujisaki.devbooks_backend.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public record CriarCategoriaDTO(
        @NotBlank
        String nome
) {
}
