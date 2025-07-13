package com.BrunoFujisaki.devbooks_backend.dto.carrinho;

import com.BrunoFujisaki.devbooks_backend.dto.livro.ListarLivroDTO;
import com.BrunoFujisaki.devbooks_backend.model.CarrinhoItem;

import java.math.BigDecimal;

public record ListarCarrinhoItemDTO(
    ListarLivroDTO livro,
    BigDecimal valor,
    Integer quantidade
) {
    public ListarCarrinhoItemDTO(CarrinhoItem i) {
        this(new ListarLivroDTO(i.getLivro()), i.getValor(), i.getQuantidade());
    }
}
