package com.BrunoFujisaki.devbooks_backend.dto.usuarios;

import com.BrunoFujisaki.devbooks_backend.model.enums.UserRole;

public record UsuarioCadastroDTO(
        String email,
        String senha,
        UserRole role
) {
}
