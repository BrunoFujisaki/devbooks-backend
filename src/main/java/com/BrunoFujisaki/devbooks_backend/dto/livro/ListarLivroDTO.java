package com.BrunoFujisaki.devbooks_backend.dto.livro;

import com.BrunoFujisaki.devbooks_backend.dto.categoria.ListarCategoriaDTO;
import com.BrunoFujisaki.devbooks_backend.model.Livro;

import java.math.BigDecimal;
import java.util.UUID;

public record ListarLivroDTO(
        UUID id,
        String titulo,
        String autor,
        ListarCategoriaDTO categoria,
        String descricao,
        Integer estoque,
        BigDecimal valor,
        String imagem
) {
    public ListarLivroDTO(Livro livro) {
        this(
            livro.getId(),
            livro.getTitulo(),
            livro.getAutor(),
            new ListarCategoriaDTO(livro.getCategoria()),
            livro.getDescricao(),
            livro.getEstoque(),
            livro.getValor(),
            livro.getImagem()
        );
    }
}
