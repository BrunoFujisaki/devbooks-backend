package com.BrunoFujisaki.devbooks_backend.controller;

import com.BrunoFujisaki.devbooks_backend.dto.AtualizarCategoriaDTO;
import com.BrunoFujisaki.devbooks_backend.dto.CriarCategoriaDTO;
import com.BrunoFujisaki.devbooks_backend.dto.ListarCategoriaDTO;
import com.BrunoFujisaki.devbooks_backend.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("categorias")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    private final CategoriaService service;

    @PostMapping
    public ResponseEntity<ListarCategoriaDTO> criarCategoria(@RequestBody @Valid CriarCategoriaDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criarCategoria(dto));
    }

    @GetMapping
    public ResponseEntity<List<ListarCategoriaDTO>> listarCategorias() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListarCategoriaDTO> getCategoria(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ListarCategoriaDTO(service.getCategoria(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListarCategoriaDTO> atualizarCategoria(@PathVariable UUID id, @RequestBody @Valid AtualizarCategoriaDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.atualizarCategoria(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable UUID id) {
        service.deletarCategoria(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
