package com.BrunoFujisaki.devbooks_backend.service;

import com.BrunoFujisaki.devbooks_backend.dto.carrinho.AdicionarAoCarrinhoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.carrinho.CarrinhoItemIdDTO;
import com.BrunoFujisaki.devbooks_backend.dto.carrinho.ListarCarrinhoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.carrinho.ListarCarrinhoItemDTO;
import com.BrunoFujisaki.devbooks_backend.infra.exception.CarrinhoException;
import com.BrunoFujisaki.devbooks_backend.infra.exception.LivroException;
import com.BrunoFujisaki.devbooks_backend.model.Carrinho;
import com.BrunoFujisaki.devbooks_backend.model.CarrinhoItem;
import com.BrunoFujisaki.devbooks_backend.model.CarrinhoItemID;
import com.BrunoFujisaki.devbooks_backend.model.Usuario;
import com.BrunoFujisaki.devbooks_backend.repository.CarrinhoItemRepository;
import com.BrunoFujisaki.devbooks_backend.repository.CarrinhoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoItemRepository carrinhoItemRepository;
    private final LivroService livroService;

    @Transactional
    public ListarCarrinhoDTO adicionarItemAoCarrinho(@Valid AdicionarAoCarrinhoDTO dto, Authentication authentication) {
        var usuario = (Usuario) authentication.getPrincipal();
        var livro = livroService.getLivro(dto.livroId());
        if (livro.getEstoque() == 0)
            throw new LivroException(livro.getTitulo()+" Sem estoque");

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
        livro.atualizarQuantidade(dto.quantidade(), false);

        List<ListarCarrinhoItemDTO> listarCarrinhoItemDTO = new ArrayList<>();
        carrinho.getItens().forEach(i -> {
            listarCarrinhoItemDTO.add(new ListarCarrinhoItemDTO(i));
        });

        return new ListarCarrinhoDTO(carrinho.getId(), usuario.getId(), carrinho.getValorTotal(), listarCarrinhoItemDTO);
    }

    public ListarCarrinhoDTO getItens(Authentication authentication) {
        var usuario = (Usuario) authentication.getPrincipal();
        var carrinho = carrinhoRepository.findByUsuarioId(usuario.getId()).orElseThrow(() ->
                new EntityNotFoundException("Carrinho não encontrado ou vazio")
        );
        List<ListarCarrinhoItemDTO> listarCarrinhoItemDTO = new ArrayList<>();
        carrinho.getItens().forEach(i ->
            listarCarrinhoItemDTO.add(new ListarCarrinhoItemDTO(i))
        );

        return new ListarCarrinhoDTO(carrinho.getId(), usuario.getId(), carrinho.getValorTotal(), listarCarrinhoItemDTO);
    }

    @Transactional
    public void retirarQuantidadeDoItem(CarrinhoItemIdDTO dto) {
        var item = carrinhoItemRepository.findById(new CarrinhoItemID(dto.livroId(), dto.carrinhoId())).orElseThrow(() ->
                new EntityNotFoundException("Item não encontrado")
        );

        if (item.getQuantidade() == 1)
            throw new CarrinhoException("A quantidade mínima do item no carrinho é 1. Para remover, use DELETE.");

        item.setQuantidade(item.getQuantidade() - 1);
        item.getLivro().atualizarQuantidade(1, true);
        item.getCarrinho().calcularValorTotal();
    }

    @Transactional
    public void removerItemDoCarrinho(CarrinhoItemIdDTO dto) {
        var item = carrinhoItemRepository.findById(new CarrinhoItemID(dto.livroId(), dto.carrinhoId())).orElseThrow(() ->
                new EntityNotFoundException("Item não encontrado")
        );
        item.getLivro().atualizarQuantidade(item.getQuantidade(), true);
        System.out.println(item.getQuantidade());
        var carrinho = item.getCarrinho();
        carrinho.getItens().remove(item);
        carrinho.calcularValorTotal();
    }

    public ListarCarrinhoDTO getCarrinho(UUID id) {
        var carrinho = carrinhoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carrinho não encontrado"));
        List<ListarCarrinhoItemDTO> listarCarrinhoItemDTO = new ArrayList<>();
        carrinho.getItens().forEach(i ->
                listarCarrinhoItemDTO.add(new ListarCarrinhoItemDTO(i))
        );

        return new ListarCarrinhoDTO(
                carrinho.getId(),
                carrinho.getUsuario().getId(),
                carrinho.getValorTotal(),
                listarCarrinhoItemDTO
        );
    }
}
