package com.BrunoFujisaki.devbooks_backend.service;

import com.BrunoFujisaki.devbooks_backend.dto.LivroDTO;
import com.BrunoFujisaki.devbooks_backend.model.Livro;
import com.BrunoFujisaki.devbooks_backend.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
    private final CategoriaService categoriaService;

    public LivroDTO criarLivro(LivroDTO dto) {
        var categoria = categoriaService.getCategoriaByNome(dto.categoria());
        return new LivroDTO(repository.save(new Livro(dto, categoria)));

    }

    public List<LivroDTO> getLivros() {
        return repository
                .findAll().stream()
                .map(LivroDTO::new).toList();
    }
}
