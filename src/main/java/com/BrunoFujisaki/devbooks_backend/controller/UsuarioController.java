package com.BrunoFujisaki.devbooks_backend.controller;

import com.BrunoFujisaki.devbooks_backend.dto.usuarios.ListarUsuarioDTO;
import com.BrunoFujisaki.devbooks_backend.dto.usuarios.UsuarioAtualizacaoDTO;
import com.BrunoFujisaki.devbooks_backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<ListarUsuarioDTO> listarUsuario(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ListarUsuarioDTO(usuarioService.getUsuario(id)));
    }

    @PutMapping
    public ResponseEntity<ListarUsuarioDTO> atualizarUsuario(@RequestBody UsuarioAtualizacaoDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioService.atualizarUsuario(dto));
    }
}
