package com.BrunoFujisaki.devbooks_backend.dto.pedido;

import com.BrunoFujisaki.devbooks_backend.dto.usuarios.EnderecoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.usuarios.ListarUsuarioDTO;
import com.BrunoFujisaki.devbooks_backend.model.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ListarPedidoDTO(
        UUID id,
        ListarUsuarioDTO usuario,
        LocalDateTime data,
        StatusPedido status,
        BigDecimal valorTotal,
        EnderecoDTO endereco,
        List<ListarPedidoItemDTO> itens
) {
}
