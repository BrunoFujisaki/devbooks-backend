package com.BrunoFujisaki.devbooks_backend.dto.carrinho;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ListarCarrinhoDTO(
        UUID id,
        UUID usuarioId,
        BigDecimal valorTotal,
        List<ListarCarrinhoItemDTO> itens
) {
}
