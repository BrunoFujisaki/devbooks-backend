package com.BrunoFujisaki.devbooks_backend.dto.usuarios;

public record UsuarioCadastroDTO(
        String nome,
        String email,
        String telefone,
        String senha
) {
}
