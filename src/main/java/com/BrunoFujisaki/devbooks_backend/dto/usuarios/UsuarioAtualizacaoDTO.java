package com.BrunoFujisaki.devbooks_backend.dto.usuarios;

import java.util.UUID;

public record UsuarioAtualizacaoDTO (
        UUID id,
        String nome,
        String email,
        String telefone,
        EnderecoDTO endereco
) {
}
