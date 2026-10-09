package com.senai.colegioplural.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String email, String perfil) {
        return Jwts.builder()
                .subject(email)
                .claim("perfil", perfil)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key())
                .compact();
    }

    public String extractEmail(String token) {
        return parser().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean isValid(String token) {
        try {
            parser().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private JwtParser parser() {
        return Jwts.parser().verifyWith(key()).build();
    }
}