package com.BrunoFujisaki.devbooks_backend.dto.carrinho;

import java.util.UUID;

public record CarrinhoItemIdDTO(
        UUID carrinhoId,
        UUID livroId
) {
}
