package com.BrunoFujisaki.devbooks_backend.dto.carrinho;

import java.util.UUID;

public record AdicionarAoCarrinhoDTO(
        UUID livroId,
        Integer quantidade
) {
}
