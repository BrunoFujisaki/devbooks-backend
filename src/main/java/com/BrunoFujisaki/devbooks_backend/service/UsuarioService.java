package com.BrunoFujisaki.devbooks_backend.service;

import com.BrunoFujisaki.devbooks_backend.dto.usuarios.AutenticacaoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.usuarios.ListarUsuarioDTO;
import com.BrunoFujisaki.devbooks_backend.dto.usuarios.UsuarioAtualizacaoDTO;
import com.BrunoFujisaki.devbooks_backend.dto.usuarios.UsuarioCadastroDTO;
import com.BrunoFujisaki.devbooks_backend.infra.security.TokenJwtDTO;
import com.BrunoFujisaki.devbooks_backend.infra.security.TokenService;
import com.BrunoFujisaki.devbooks_backend.model.Usuario;
import com.BrunoFujisaki.devbooks_backend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final AuthenticationManager manager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public TokenJwtDTO logarUsuario(AutenticacaoDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = manager.authenticate(authenticationToken);

        return tokenService.gerarToken((Usuario) authentication.getPrincipal());
    }

    @Transactional
    public ListarUsuarioDTO cadastrarUsuario(UsuarioCadastroDTO dto) {
        var senha = new BCryptPasswordEncoder().encode(dto.senha());
        return new ListarUsuarioDTO(usuarioRepository.save(new Usuario(dto, senha)));
    }

    public Usuario getUsuario(UUID id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Usuário não encontrado")
        );
    }

    @Transactional
    public ListarUsuarioDTO atualizarUsuario(UsuarioAtualizacaoDTO dto) {
        var usuario = getUsuario(dto.id());
        usuario.atualizar(dto);
        return new ListarUsuarioDTO(usuario);
    }

    public List<ListarUsuarioDTO> getUsuarios() {
        return usuarioRepository
                .findAll()
                .stream()
                .map(ListarUsuarioDTO::new)
                .toList();
    }
}
