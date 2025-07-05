package com.BrunoFujisaki.devbooks_backend.repository;

import com.BrunoFujisaki.devbooks_backend.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {
    boolean existsByNome(String nome);

    Optional<Categoria> findByNome(String categoria);
}
