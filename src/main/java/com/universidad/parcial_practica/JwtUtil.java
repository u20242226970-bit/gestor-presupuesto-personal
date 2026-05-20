package com.universidad.parcial_practica;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * Clase utilitaria para la gestión de tokens JWT.
 * Se encarga de generar, parsear y validar tokens firmados con HMAC-SHA256.
 */
@Component
public class JwtUtil {

	@Value("${jwt.secret:Y2xhdmVfc2VjcmV0YV9maW5hbmNlcmFua19wcm95ZWN0b191c2NvXzIwMjZfbWFzX2RlXzI1NmJpdHM=}")
	private String secret;

	@Value("${jwt.expiration:86400000}")
	private long expiration;

    private SecretKey key;

    /**
     * Convierte el secret de Base64 a una SecretKey utilizable por jjwt.
     * Se ejecuta una sola vez al arrancar la aplicación.
     */
    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Genera un token JWT firmado para el username dado.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    /**
     * Extrae el username (subject) de un token JWT.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae la fecha de expiración del token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Método genérico para extraer cualquier dato (claim) del token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return resolver.apply(claims);
    }

    /**
     * Indica si el token ya expiró.
     */
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Valida que el token corresponda al username dado y no esté expirado.
     */
    public boolean validateToken(String token, String username) {
        try {
            String tokenUsername = extractUsername(token);
            return tokenUsername.equals(username) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}