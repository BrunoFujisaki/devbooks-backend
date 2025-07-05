package com.BrunoFujisaki.devbooks_backend.service;

import com.BrunoFujisaki.devbooks_backend.dto.CriarLivroDTO;
import com.BrunoFujisaki.devbooks_backend.dto.ListarLivroDTO;
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

    public ListarLivroDTO criarLivro(CriarLivroDTO dto) {
        var categoria = categoriaService.getCategoria(dto.categoria());
        categoria.setQuantidadeLivros(dto.estoque());
        return new ListarLivroDTO(repository.save(new Livro(dto, categoria)));
    }

    public List<ListarLivroDTO> getLivros() {
        return repository
                .findAll().stream()
                .map(ListarLivroDTO::new).toList();
    }
}
