package com.BrunoFujisaki.devbooks_backend.dto.carrinho;

import com.BrunoFujisaki.devbooks_backend.model.CarrinhoItem;

import java.math.BigDecimal;
import java.util.UUID;

public record ListarCarrinhoItemDTO(
    UUID livroId,
    BigDecimal valor,
    Integer quantidade
) {
    public ListarCarrinhoItemDTO(CarrinhoItem i) {
        this(i.getLivro().getId(), i.getValor(), i.getQuantidade());
    }
}
