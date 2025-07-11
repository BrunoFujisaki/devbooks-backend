package com.BrunoFujisaki.devbooks_backend.dto.usuarios;

import com.BrunoFujisaki.devbooks_backend.model.Endereco;

public record EnderecoDTO(
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {
    public EnderecoDTO(Endereco endereco) {
        this(
            endereco.getCep(),
            endereco.getLogradouro(),
            endereco.getNumero(),
            endereco.getComplemento(),
            endereco.getBairro(),
            endereco.getLocalidade(),
            endereco.getUf()
        );
    }
}
