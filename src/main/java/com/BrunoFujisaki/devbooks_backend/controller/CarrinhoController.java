package com.BrunoFujisaki.devbooks_backend.controller;

import com.BrunoFujisaki.devbooks_backend.dto.carrinho.AdicionarAoCarrinhoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.carrinho.ListarCarrinhoDTO;
import com.BrunoFujisaki.devbooks_backend.service.CarrinhoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("carrinhos")
@RequiredArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @PostMapping
    public ResponseEntity<ListarCarrinhoDTO> adicionarItemAoCarrinho(@RequestBody @Valid AdicionarAoCarrinhoDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carrinhoService.adicionarItemAoCarrinho(dto));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<ListarCarrinhoDTO> listarItens(@PathVariable UUID usuarioId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carrinhoService.getItens(usuarioId));
    }
}
