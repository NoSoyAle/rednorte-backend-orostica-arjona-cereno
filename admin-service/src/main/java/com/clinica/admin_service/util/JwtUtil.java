package com.clinica.admin_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
<<<<<<< HEAD
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

<<<<<<< HEAD
=======
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractRole(String token) {
<<<<<<< HEAD
        return extractAllClaims(token).get("role", String.class);
=======
        String role = extractAllClaims(token).get("role", String.class);
        logger.info("Role extraido del token: " + role);
        return role;
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
<<<<<<< HEAD
        return createToken(claims, username);
=======
        logger.info("Generando token para username: " + username + ", role: " + role);
        String token = createToken(claims, username);
        logger.info("Token generado con longitud: " + token.length());
        return token;
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    public Boolean validateToken(String token) {
        try {
<<<<<<< HEAD
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
=======
            Claims claims = extractAllClaims(token);
            boolean expired = isTokenExpired(token);
            logger.info("Token validado - Subject: " + claims.getSubject() + ", Expirado: " + expired);
            return !expired;
        } catch (Exception e) {
            logger.error("Error al validar token: " + e.getClass().getSimpleName() + " - " + e.getMessage());
>>>>>>> 0ce737d3fdd4d17416de0646b83f3901e9f1a661
            return false;
        }
    }
}
