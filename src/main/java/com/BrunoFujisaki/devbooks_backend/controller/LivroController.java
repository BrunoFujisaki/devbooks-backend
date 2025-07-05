package com.BrunoFujisaki.devbooks_backend.controller;

import com.BrunoFujisaki.devbooks_backend.dto.livro.AtualizarLivroDTO;
import com.BrunoFujisaki.devbooks_backend.dto.livro.CriarLivroDTO;
import com.BrunoFujisaki.devbooks_backend.dto.livro.ListarLivroDTO;
import com.BrunoFujisaki.devbooks_backend.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LivroController {

    private final LivroService service;

    @PostMapping
    public ResponseEntity<ListarLivroDTO> criarLivro(@RequestBody @Valid CriarLivroDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criarLivro(dto));
    }

    @GetMapping
    public ResponseEntity<List<ListarLivroDTO>> listarLivros() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getLivros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListarLivroDTO> getLivro(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ListarLivroDTO(service.getLivro(id)));
    }

    @PutMapping
    public ResponseEntity<ListarLivroDTO> atualizarLivro(@RequestBody @Valid AtualizarLivroDTO dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.atualizarLivro(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable UUID id) {
        service.deletarLivro(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
