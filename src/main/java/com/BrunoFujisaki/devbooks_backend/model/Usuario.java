package com.BrunoFujisaki.devbooks_backend.model;


import com.BrunoFujisaki.devbooks_backend.dto.usuarios.UsuarioAtualizacaoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.usuarios.UsuarioCadastroDTO;
import com.BrunoFujisaki.devbooks_backend.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @UuidGenerator
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    @Setter
    private String senha;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public Usuario(UsuarioCadastroDTO dto, String senha) {
        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.senha = senha;
        this.role = UserRole.USER;
    }

    public void atualizar(UsuarioAtualizacaoDTO dto) {
        if (dto.nome() != null && !dto.nome().isBlank()) {
            this.nome = dto.nome();
        }
        if (dto.email() != null && !dto.email().isBlank()) {
            this.email = dto.email();
        }
        if (dto.telefone() != null && !dto.telefone().isBlank()) {
            this.telefone = dto.telefone();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role == UserRole.ADMIN
            ? List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("USER"))
            : List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
