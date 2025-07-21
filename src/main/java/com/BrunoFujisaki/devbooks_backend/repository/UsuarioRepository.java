package com.BrunoFujisaki.devbooks_backend.repository;

import com.BrunoFujisaki.devbooks_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    UserDetails findByEmail(String email);

    @Query("SELECT COUNT(u.id) FROM Usuario u")
    Long totalDeUsuarios();

    Boolean existsByEmail(String email);
}
