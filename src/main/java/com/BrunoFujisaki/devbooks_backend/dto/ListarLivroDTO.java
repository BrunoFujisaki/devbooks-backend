package com.BrunoFujisaki.devbooks_backend.dto;

import com.BrunoFujisaki.devbooks_backend.model.Livro;

import java.math.BigDecimal;

public record ListarLivroDTO(
        String titulo,
        String autor,
        CriarCategoriaDTO categoria,
        String descricao,
        Integer estoque,
        BigDecimal valor,
        String imagem
) {
    public ListarLivroDTO(Livro livro) {
        this(
            livro.getTitulo(),
            livro.getAutor(),
            new CriarCategoriaDTO(livro.getCategoria()),
            livro.getDescricao(),
            livro.getEstoque(),
            livro.getValor(),
            livro.getImagem()
        );
    }
}
