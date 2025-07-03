package com.BrunoFujisaki.devbooks_backend.service;

import com.BrunoFujisaki.devbooks_backend.dto.CategoriaDTO;
import com.BrunoFujisaki.devbooks_backend.infra.exception.CategoriaException;
import com.BrunoFujisaki.devbooks_backend.model.Categoria;
import com.BrunoFujisaki.devbooks_backend.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    @Transactional
    public CategoriaDTO criarCategoria(CategoriaDTO dto) {
        if (repository.existsByNome(dto.nome())) {
            throw new CategoriaException("Categoria já existe.");
        }

        return new CategoriaDTO(repository.save(new Categoria(dto.nome())));
    }

    public Categoria getCategoriaByNome(String categoria) {
        return repository.findByNome(categoria).orElseThrow(() -> {
            throw new EntityNotFoundException("Categoria não encontrada.");
        });
    }

    public List<CategoriaDTO> getCategorias() {
        return repository
                .findAll().stream()
                .map(CategoriaDTO::new).toList();
    }

    @Transactional
    public CategoriaDTO atualizarCategoria(@Valid CategoriaDTO dto) {
        if (repository.existsByNome(dto.nome())) {
            throw new CategoriaException("Categoria já existe.");
        }
        var categoria = repository.findById(dto.id()).orElseThrow(() ->
            new EntityNotFoundException("Categoria não encontrada.")
        );
        categoria.atualizar(dto);

        return new CategoriaDTO(categoria);
    }

    public CategoriaDTO getCategoria(Integer id) {
        return new CategoriaDTO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada.")
        ));
    }
}
