package com.malucos.users.service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Servicio encargado de la gestión de JSON WEB TOKEN
 * Proporciona métodos de creación, validación y lectura de información del
 * token
 */
@Service
public class JwtService {

    /**
     * Clave secreta
     */
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    /**
     * Expiración del token
     */
    @Value("${security.jwt.token-expiration}")
    private Long tokenExpiration;

    /**
     * Transforma la clave secreta de string (BASE64) a un objeto SecretKey
     * utilizable por la librería
     * 
     * @return SecretKey
     */
    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Método para la creación del token (jwt)
     * 
     * @param userId   id del usuario
     * @param username nombre del usuario (dueño del token)
     * @param rolId    id del rol del usuario
     * @return String JWT
     */
    public String generateToken(Long userId, String username, Long rolId) {
        return Jwts.builder() // Empezamos a construir el token
                .claims(Map.of("userId", userId)) // Agregamos datos personalizadios (payload)
                .claims(Map.of("rolId", rolId)) // Agregamos datos personalizadios (payload)
                .subject(username) // Identificamos al dueño del token
                .issuedAt(new Date()) // Fecha de creación
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration)) // Fecha de expiración (fecha actual
                                                                                    // expresada en ms + tiempo de
                                                                                    // expiracion en ms)
                .signWith(getSignKey()) // Firma digital para evitar alteracion del token
                .compact(); // Unimos todo lo que acabamos de construir
    }

    /**
     * Verifica si el token es válido y no ha sido manipulado
     * 
     * @param token el jwt a validar
     * @return true si la firma es válida, falso en caso contrario
     */
    public Boolean istokenValid(String token) {
        try {
            Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método genérico para extraer todos los claims del token (payload)
     * 
     * @param <T>      cualquier tipado
     * @param token    actual
     * @param resolver claim específico
     * @return
     */
    private <T> T extractClaims(String token, Function<Claims, T> resolver) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return resolver.apply(claims);
    }

    /**
     * Método para extraer el propietario del token (jwt)
     * 
     * @param token actual
     * @return nombre de usuario
     */
    public String exctractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    /**
     * Método para extraer el id del usuario del token (jwt)
     * 
     * @param token
     * @return
     */
    public Long extractUserId(String token) {
        return extractClaims(token, claims -> claims.get("userId", Long.class));
    }

    /**
     * Método para extraer el id del rol del usuario del token (jwt)
     * 
     * @param token
     * @return
     */
    public Long extractRolId(String token) {
        return extractClaims(token, claims -> claims.get("rolId", Long.class));
    }

    /**
     * Método para refrescar el token
     * 
     * @param token
     * @return token nuevo
     * @throws Exception
     */
    public String refreshToken(String token) throws Exception {
        Claims claims;

        try {
            claims = Jwts.parser()
                    .verifyWith(getSignKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new Exception("Token is expired" + e.getMessage());
        } catch (JwtException e) {
            e.printStackTrace();
            throw new Exception("Token is invalid" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Hubo un error al validar el token" + e.getMessage());
        }

        return generateToken(claims.get("userId", Long.class), claims.getSubject(), claims.get("rolId", Long.class));
    }
}
