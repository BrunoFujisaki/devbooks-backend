package com.BrunoFujisaki.devbooks_backend.infra.security;

import com.BrunoFujisaki.devbooks_backend.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${security.token.secret}")
    private String secret;

    public TokenJwtDTO gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return new TokenJwtDTO(JWT.create()
                    .withIssuer("API DevBooks")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(genExpirateDateTime())
                    .sign(algorithm));
        } catch (JWTCreationException exception){
            throw new RuntimeException("Falha ao gerar token");
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API DevBooks")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido ou expirado.");
        }
    }

    private Instant genExpirateDateTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
