package com.BrunoFujisaki.devbooks_backend.repository;

import com.BrunoFujisaki.devbooks_backend.model.Categoria;
import com.BrunoFujisaki.devbooks_backend.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    @Query("SELECT COUNT(l.id) FROM Livro l")
    Long totalDeLivros();

    boolean existsByCategoria(Categoria categoria);
}
