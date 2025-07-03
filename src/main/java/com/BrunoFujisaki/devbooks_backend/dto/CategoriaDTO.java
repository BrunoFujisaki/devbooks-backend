package com.BrunoFujisaki.devbooks_backend.dto;

import com.BrunoFujisaki.devbooks_backend.model.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(
        Integer id,
        @NotBlank
        String nome,
        Integer quantidadeLivros
) {
    public CategoriaDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.getQuantidadeLivros());
    }
}
