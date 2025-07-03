package com.BrunoFujisaki.devbooks_backend.controller;

import com.BrunoFujisaki.devbooks_backend.dto.CategoriaDTO;
import com.BrunoFujisaki.devbooks_backend.dto.LivroDTO;
import com.BrunoFujisaki.devbooks_backend.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categorias")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {

    private final CategoriaService service;

    @PostMapping
    public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody @Valid CategoriaDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criarCategoria(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoria(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getCategoria(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@RequestBody @Valid CategoriaDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.atualizarCategoria(dto));
    }
}
