package com.BrunoFujisaki.devbooks_backend.dto.pedido;

import com.BrunoFujisaki.devbooks_backend.dto.livro.ListarLivroDTO;
import com.BrunoFujisaki.devbooks_backend.model.PedidoItem;

import java.math.BigDecimal;

public record ListarPedidoItemDTO(
        ListarLivroDTO livro,
        BigDecimal valor,
        Integer quantidade
) {
    public ListarPedidoItemDTO(PedidoItem i) {
        this(new ListarLivroDTO(
                i.getLivro()),
                i.getValor(),
                i.getQuantidade()
        );
    }
}
