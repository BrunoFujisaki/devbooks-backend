package com.BrunoFujisaki.devbooks_backend.controller;

import com.BrunoFujisaki.devbooks_backend.dto.carrinho.AdicionarAoCarrinhoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.carrinho.CarrinhoItemIdDTO;
import com.BrunoFujisaki.devbooks_backend.dto.carrinho.ListarCarrinhoDTO;
import com.BrunoFujisaki.devbooks_backend.service.CarrinhoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("carrinhos")
@RequiredArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @PostMapping
    public ResponseEntity<ListarCarrinhoDTO> adicionarItemAoCarrinho(@RequestBody @Valid AdicionarAoCarrinhoDTO dto, Authentication authentication) {
        System.out.println("Adicionando");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carrinhoService.adicionarItemAoCarrinho(dto, authentication));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<ListarCarrinhoDTO> listarItens(@PathVariable UUID usuarioId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carrinhoService.getItens(usuarioId));
    }

    @PatchMapping
    public ResponseEntity<Void> retirarQuantidadeDoCarrinho(@RequestBody CarrinhoItemIdDTO dto) {
        carrinhoService.retirarQuantidadeDoItem(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removerItemDoCarrinho(@RequestBody CarrinhoItemIdDTO dto) {
        carrinhoService.removerItemDoCarrinho(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
