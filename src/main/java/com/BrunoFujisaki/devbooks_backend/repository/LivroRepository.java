package com.BrunoFujisaki.devbooks_backend.repository;

import com.BrunoFujisaki.devbooks_backend.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
