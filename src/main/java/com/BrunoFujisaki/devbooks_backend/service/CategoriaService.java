package com.BrunoFujisaki.devbooks_backend.service;

import com.BrunoFujisaki.devbooks_backend.dto.CategoriaDTO;
import com.BrunoFujisaki.devbooks_backend.infra.exception.CategoriaException;
import com.BrunoFujisaki.devbooks_backend.model.Categoria;
import com.BrunoFujisaki.devbooks_backend.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaDTO criarCategoria(CategoriaDTO dto) {
        if (repository.existsByNome(dto.nome())) {
            throw new CategoriaException("Categoria já existe.");
        }

        return new CategoriaDTO(repository.save(new Categoria(dto.nome())));
    }

}
