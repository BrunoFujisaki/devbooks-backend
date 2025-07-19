package com.BrunoFujisaki.devbooks_backend.service;

import com.BrunoFujisaki.devbooks_backend.dto.estatisticas.EstatisticasDTO;
import com.BrunoFujisaki.devbooks_backend.model.enums.StatusPedido;
import com.BrunoFujisaki.devbooks_backend.repository.LivroRepository;
import com.BrunoFujisaki.devbooks_backend.repository.PedidoRepository;
import com.BrunoFujisaki.devbooks_backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstatisticasService {

    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;
    private final PedidoRepository pedidoRepository;

    public EstatisticasDTO getEstatisticas() {
        var totalDeVendas = pedidoRepository.totalDeVendas(StatusPedido.PAGO);
        var totalDeLivros = livroRepository.totalDeLivros();
        var totalDeUsuarios = usuarioRepository.totalDeUsuarios();
        return new EstatisticasDTO(totalDeVendas, totalDeLivros, totalDeUsuarios);
    }
}
