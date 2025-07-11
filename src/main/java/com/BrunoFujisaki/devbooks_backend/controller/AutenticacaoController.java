package com.BrunoFujisaki.devbooks_backend.controller;

import com.BrunoFujisaki.devbooks_backend.dto.usuarios.AutenticacaoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.usuarios.ListarUsuarioDTO;
import com.BrunoFujisaki.devbooks_backend.dto.usuarios.UsuarioCadastroDTO;
import com.BrunoFujisaki.devbooks_backend.infra.security.TokenJwtDTO;
import com.BrunoFujisaki.devbooks_backend.infra.security.TokenService;
import com.BrunoFujisaki.devbooks_backend.model.Usuario;
import com.BrunoFujisaki.devbooks_backend.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final UsuarioService service;

    @PostMapping("/cadastro")
    public ResponseEntity<ListarUsuarioDTO> fazerCadastro(@RequestBody @Valid UsuarioCadastroDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.cadastrarUsuario(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenJwtDTO> fazerLogin(@RequestBody @Valid AutenticacaoDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.logarUsuario(dto));
    }
}
