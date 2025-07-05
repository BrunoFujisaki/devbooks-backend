package com.BrunoFujisaki.devbooks_backend.service;

import com.BrunoFujisaki.devbooks_backend.dto.AtualizarCategoriaDTO;
import com.BrunoFujisaki.devbooks_backend.dto.CriarCategoriaDTO;
import com.BrunoFujisaki.devbooks_backend.dto.CriarLivroDTO;
import com.BrunoFujisaki.devbooks_backend.dto.ListarCategoriaDTO;
import com.BrunoFujisaki.devbooks_backend.infra.exception.CategoriaException;
import com.BrunoFujisaki.devbooks_backend.model.Categoria;
import com.BrunoFujisaki.devbooks_backend.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    @Transactional
    public ListarCategoriaDTO criarCategoria(CriarCategoriaDTO dto) {
        if (repository.existsByNome(dto.nome())) {
            throw new CategoriaException("Categoria já existe.");
        }

        return new ListarCategoriaDTO(repository.save(new Categoria(dto.nome())));
    }

    public List<ListarCategoriaDTO> getCategorias() {
        return repository
                .findAll().stream()
                .map(ListarCategoriaDTO::new).toList();
    }

    @Transactional
    public ListarCategoriaDTO atualizarCategoria(UUID id, AtualizarCategoriaDTO dto) {
        if (repository.existsByNome(dto.nome())) {
            throw new CategoriaException("Categoria já existe.");
        }
        var categoria = repository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Categoria não encontrada.")
        );
        categoria.atualizar(dto);

        return new ListarCategoriaDTO(categoria);
    }

    public Categoria getCategoria(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada.")
        );
    }

    @Transactional
    public void deletarCategoria(UUID id) {
        var categoria = repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Categoria não encontrada.");
        });

        repository.delete(categoria);
    }
}
