package com.BrunoFujisaki.devbooks_backend.dto.pedido;

import com.BrunoFujisaki.devbooks_backend.model.enums.StatusPedido;

import java.util.UUID;

public record PedidoStatusDTO(
        UUID id,
        StatusPedido status
) {
}
