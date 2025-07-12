package com.BrunoFujisaki.devbooks_backend.dto.usuarios;

import com.BrunoFujisaki.devbooks_backend.model.Usuario;
import com.BrunoFujisaki.devbooks_backend.model.enums.UserRole;

import java.util.UUID;

public record ListarUsuarioDTO(
        UUID id,
        String nome,
        String email,
        String telefone,
        String senha,
        UserRole role,
        EnderecoDTO endereco
) {
    public ListarUsuarioDTO(Usuario usuario) {
        this(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getTelefone(),
            usuario.getSenha(),
            usuario.getRole(),
            new EnderecoDTO(usuario.getEndereco())
        );
    }
}
