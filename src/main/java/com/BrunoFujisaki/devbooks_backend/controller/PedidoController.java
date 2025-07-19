package com.BrunoFujisaki.devbooks_backend.controller;

import com.BrunoFujisaki.devbooks_backend.dto.pedido.ListarPedidoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.pedido.PedidoStatusDTO;
import com.BrunoFujisaki.devbooks_backend.dto.usuarios.EnderecoDTO;
import com.BrunoFujisaki.devbooks_backend.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    public ResponseEntity<ListarPedidoDTO> realizarPedido(Authentication authentication, @RequestBody EnderecoDTO endereco) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.realizarPedido(authentication, endereco));
    }

    @PatchMapping
    public ResponseEntity<Void> atualizarStatusPedido(@RequestBody PedidoStatusDTO dto) {
        service.atualizarStatus(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListarPedidoDTO> listarPedido(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.listarPedido(id));
    }

    @GetMapping
    public ResponseEntity<List<ListarPedidoDTO>> listarPedidosDoUsuario(Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.listarPedidos(authentication));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListarPedidoDTO>> listarPedidos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.listarTodosPedidos());
    }
}
