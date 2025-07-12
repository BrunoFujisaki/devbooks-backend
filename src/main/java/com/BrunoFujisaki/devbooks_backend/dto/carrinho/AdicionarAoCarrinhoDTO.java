package com.BrunoFujisaki.devbooks_backend.dto.carrinho;

import java.util.UUID;

public record AdicionarAoCarrinhoDTO(
        UUID usuarioId,
        UUID livroId,
        Integer quantidade
) {
}
