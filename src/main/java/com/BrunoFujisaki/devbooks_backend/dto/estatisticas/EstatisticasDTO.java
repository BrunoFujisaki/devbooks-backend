package com.BrunoFujisaki.devbooks_backend.dto.estatisticas;

import java.math.BigDecimal;

public record EstatisticasDTO(
        BigDecimal totalDeVendas,
        Long totalDeLivros,
        Long totalDeUsuarios
) {
}
