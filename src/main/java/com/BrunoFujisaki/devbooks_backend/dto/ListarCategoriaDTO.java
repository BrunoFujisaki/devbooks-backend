package com.BrunoFujisaki.devbooks_backend.dto;

import com.BrunoFujisaki.devbooks_backend.model.Categoria;

import java.util.UUID;

public record ListarCategoriaDTO(
    UUID id,
    String nome,
    Integer quantidade
) {
    public ListarCategoriaDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome(), categoria.getQuantidadeLivros());
    }
}
