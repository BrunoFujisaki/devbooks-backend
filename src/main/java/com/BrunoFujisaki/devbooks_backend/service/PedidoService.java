package com.BrunoFujisaki.devbooks_backend.service;

import com.BrunoFujisaki.devbooks_backend.dto.pedido.ListarPedidoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.pedido.ListarPedidoItemDTO;
import com.BrunoFujisaki.devbooks_backend.dto.pedido.PedidoStatusDTO;
import com.BrunoFujisaki.devbooks_backend.dto.usuarios.EnderecoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.usuarios.ListarUsuarioDTO;
import com.BrunoFujisaki.devbooks_backend.infra.exception.CarrinhoException;
import com.BrunoFujisaki.devbooks_backend.infra.exception.PedidoException;
import com.BrunoFujisaki.devbooks_backend.model.*;
import com.BrunoFujisaki.devbooks_backend.model.enums.StatusPedido;
import com.BrunoFujisaki.devbooks_backend.model.enums.UserRole;
import com.BrunoFujisaki.devbooks_backend.repository.CarrinhoRepository;
import com.BrunoFujisaki.devbooks_backend.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CarrinhoRepository carrinhoRepository;

    @Transactional
    public ListarPedidoDTO realizarPedido(Authentication authentication, EnderecoDTO endereco) {
        var usuario = (Usuario) authentication.getPrincipal();
        var carrinho = carrinhoRepository.findByUsuarioId(usuario.getId()).orElseThrow(() -> new EntityNotFoundException("Carrinho não encontrado"));
        if (carrinho.getItens().isEmpty())
            throw new CarrinhoException("Carrinho vazio");

        var pedido = new Pedido(
                carrinho.getUsuario(),
                StatusPedido.PAGO,
                LocalDateTime.now(),
                endereco
        );
        pedidoRepository.save(pedido);

        carrinho.getItens().forEach(i -> {
            var pedidoItemID = new PedidoItemID();
            pedidoItemID.setPedidoId(pedido.getId());
            pedidoItemID.setLivroId(i.getLivro().getId());
            pedido.getItens().add(new PedidoItem(
                    pedidoItemID,
                    pedido,
                    i.getLivro(),
                    i.getValor(),
                    i.getQuantidade()
            ));
        });

        carrinho.limparCarrinho();
        pedido.calcularValorTotal();
        return transformarPedidoEmDTO(pedido);
    }

    public ListarPedidoDTO listarPedido(UUID id) {
        var pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        return transformarPedidoEmDTO(pedido);
    }

    public ListarPedidoDTO transformarPedidoEmDTO(Pedido pedido) {
        List<ListarPedidoItemDTO> listarPedidoItemDTO = new ArrayList<>();
        pedido.getItens().forEach(i -> {
            listarPedidoItemDTO.add(new ListarPedidoItemDTO(i));
        });

        return new ListarPedidoDTO(
                pedido.getId(),
                new ListarUsuarioDTO(pedido.getUsuario()),
                pedido.getData(),
                pedido.getStatus(),
                pedido.getValorTotal(),
                new EnderecoDTO(pedido.getEndereco()),
                listarPedidoItemDTO
        );
    }


    @Transactional
    public void atualizarStatus(PedidoStatusDTO dto) {
        var pedido = pedidoRepository.findById(
                dto.id()).orElseThrow(() ->
                    new EntityNotFoundException("Pedido não encontrado")
                );
        if (pedido.getStatus() != StatusPedido.PROCESSANDO)
            throw new PedidoException("Não é possível pagar pedidos que não estejam em processamento");
        pedido.setStatus(dto.status());
    }

    public List<ListarPedidoDTO> listarPedidos(Authentication authentication) {
        var usuario = (Usuario) authentication.getPrincipal();
        var pedidos =  pedidoRepository.findAllByUsuarioId(usuario.getId());
        return transformarPedidosEmDTO(pedidos);
    }

    public List<ListarPedidoDTO> listarTodosPedidos() {
        var pedidos =  pedidoRepository.findAll();
        return transformarPedidosEmDTO(pedidos);
    }

    public List<ListarPedidoDTO> transformarPedidosEmDTO(List<Pedido> pedidos) {
        return pedidos.stream().map(pedido -> new ListarPedidoDTO(
                pedido.getId(),
                new ListarUsuarioDTO(pedido.getUsuario()),
                pedido.getData(),
                pedido.getStatus(),
                pedido.getValorTotal(),
                new EnderecoDTO(pedido.getEndereco()),
                pedido.getItens().stream()
                        .map(ListarPedidoItemDTO::new)
                        .toList()
        )).toList();
    }
}
