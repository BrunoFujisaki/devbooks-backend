package com.BrunoFujisaki.devbooks_backend.service;

import com.BrunoFujisaki.devbooks_backend.dto.livro.AtualizarLivroDTO;
import com.BrunoFujisaki.devbooks_backend.dto.livro.CriarLivroDTO;
import com.BrunoFujisaki.devbooks_backend.dto.livro.ListarLivroDTO;
import com.BrunoFujisaki.devbooks_backend.model.Livro;
import com.BrunoFujisaki.devbooks_backend.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;
    private final CategoriaService categoriaService;

    @Transactional
    public ListarLivroDTO criarLivro(CriarLivroDTO dto) {
        var categoria = categoriaService.getCategoria(dto.categoria());
        return new ListarLivroDTO(repository.save(new Livro(dto, categoria)));
    }

    public List<ListarLivroDTO> getLivros() {
        return repository
                .findAll().stream()
                .map(ListarLivroDTO::new).toList();
    }

    public Livro getLivro(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Livro não encontrado."));
    }

    @Transactional
    public ListarLivroDTO atualizarLivro(AtualizarLivroDTO dto) {
        var categoria = categoriaService.getCategoria(dto.categoria());
        var livro = getLivro(dto.id());
        livro.atualizar(dto, categoria);
        return new ListarLivroDTO(livro);
    }

    @Transactional
    public void deletarLivro(UUID id) {
        var livro = getLivro(id);
        repository.delete(livro);
    }
}
