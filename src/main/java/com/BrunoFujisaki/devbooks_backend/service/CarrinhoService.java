package com.BrunoFujisaki.devbooks_backend.service;

import com.BrunoFujisaki.devbooks_backend.dto.carrinho.AdicionarAoCarrinhoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.carrinho.ListarCarrinhoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.carrinho.ListarCarrinhoItemDTO;
import com.BrunoFujisaki.devbooks_backend.model.Carrinho;
import com.BrunoFujisaki.devbooks_backend.model.CarrinhoItem;
import com.BrunoFujisaki.devbooks_backend.model.CarrinhoItemID;
import com.BrunoFujisaki.devbooks_backend.repository.CarrinhoItemRepository;
import com.BrunoFujisaki.devbooks_backend.repository.CarrinhoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoItemRepository carrinhoItemRepository;
    private final UsuarioService usuarioService;
    private final LivroService livroService;

    @Transactional
    public ListarCarrinhoDTO adicionarItemAoCarrinho(@Valid AdicionarAoCarrinhoDTO dto) {
        var usuario = usuarioService.getUsuario(dto.usuarioId());
        var livro = livroService.getLivro(dto.livroId());
        var carrinho = carrinhoRepository.findByUsuarioId(usuario.getId())
                .orElseGet(() -> {
                        var newCarrinho = new Carrinho();
                        newCarrinho.setUsuario(usuario);
                        carrinhoRepository.save(newCarrinho);
                        return newCarrinho;
                });

        var itemId = new CarrinhoItemID();
        itemId.setCarrinhoId(carrinho.getId());
        itemId.setLivroId(livro.getId());
        var itemOptional = carrinhoItemRepository.findById(itemId);
        if (itemOptional.isPresent()) {
            var item = itemOptional.get();
            item.setQuantidade(item.getQuantidade() + dto.quantidade());
        } else {
            var item = new CarrinhoItem(
                    itemId,
                    livro,
                    carrinho,
                    livro.getValor(),
                    dto.quantidade()
            );
            carrinho.getItens().add(item);
        }
        carrinho.calcularValorTotal();
        livro.atualizarQuantidade(dto.quantidade());

        List<ListarCarrinhoItemDTO> listarCarrinhoItemDTO = new ArrayList<>();
        carrinho.getItens().forEach(i -> {
            listarCarrinhoItemDTO.add(new ListarCarrinhoItemDTO(i));
        });

        return new ListarCarrinhoDTO(usuario.getId(), carrinho.getValorTotal(), listarCarrinhoItemDTO);
    }

    public ListarCarrinhoDTO getItens(UUID usuarioId) {
        var usuario = usuarioService.getUsuario(usuarioId);
        var carrinho = carrinhoRepository.findByUsuarioId(usuarioId).orElseThrow(() ->
                new EntityNotFoundException("Carrinho não encontrado ou vazio")
        );
        List<ListarCarrinhoItemDTO> listarCarrinhoItemDTO = new ArrayList<>();
        carrinho.getItens().forEach(i -> {
            listarCarrinhoItemDTO.add(new ListarCarrinhoItemDTO(i));
        });

        return new ListarCarrinhoDTO(usuario.getId(), carrinho.getValorTotal(), listarCarrinhoItemDTO);
    }
}
