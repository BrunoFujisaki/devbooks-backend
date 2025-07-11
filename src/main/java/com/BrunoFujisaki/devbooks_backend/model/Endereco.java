package com.BrunoFujisaki.devbooks_backend.model;

import com.BrunoFujisaki.devbooks_backend.dto.usuarios.EnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Endereco {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    public Endereco(EnderecoDTO dto) {
        this.cep = dto.cep();
        this.logradouro = dto.logradouro();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.bairro = dto.bairro();
        this.localidade = dto.localidade();
        this.uf = dto.uf();
    }

    public void atualizar(EnderecoDTO dto) {
        if (dto.cep() != null && !dto.cep().isBlank())
            this.cep = dto.cep();

        if (dto.logradouro() != null && !dto.logradouro().isBlank())
            this.logradouro = dto.logradouro();

        if (dto.numero() != null && !dto.numero().isBlank())
            this.numero = dto.numero();

        if (dto.complemento() != null && !dto.complemento().isBlank())
            this.complemento = dto.complemento();

        if (dto.bairro() != null && !dto.bairro().isBlank())
            this.bairro = dto.bairro();

        if (dto.localidade() != null && !dto.localidade().isBlank())
            this.localidade = dto.localidade();

        if (dto.uf() != null && !dto.uf().isBlank())
            this.uf = dto.uf();
    }
}
