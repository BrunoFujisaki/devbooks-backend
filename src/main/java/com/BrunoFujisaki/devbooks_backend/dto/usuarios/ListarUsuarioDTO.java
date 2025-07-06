package com.BrunoFujisaki.devbooks_backend.dto.usuarios;

import com.BrunoFujisaki.devbooks_backend.model.Usuario;
import com.BrunoFujisaki.devbooks_backend.model.enums.UserRole;

import java.util.UUID;

public record ListarUsuarioDTO(
        UUID id,
        String email,
        String senha,
        UserRole role
) {
    public ListarUsuarioDTO(Usuario usuario) {
        this(
            usuario.getId(),
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.getRole()
        );
    }
}
