package com.BrunoFujisaki.devbooks_backend.dto;

import com.BrunoFujisaki.devbooks_backend.model.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CriarCategoriaDTO(
        @NotBlank
        String nome
) {
    public CriarCategoriaDTO(Categoria categoria) {
        this(categoria.getNome());
    }
}
