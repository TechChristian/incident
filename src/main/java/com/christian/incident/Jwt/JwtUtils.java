package com.christian.incident.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j

public class JwtUtils {
    // -> Prefixo Header
    public static final String JWT_BEARER = "Bearer ";

    // -> Http que carrega o Token
    public static final String JWT_AUTHORIZATION = "Authorization";

    // -> Chave secreta usada para assinar e validar o token JWT
    public static final String SECRET_KEY = "0123456789-012345679-01234568789";

    public static final long EXPIRE_DAYS = 0;
    public static final long EXPIRE_HOURS = 0;
    public static final long EXPIRE_MINUTES = 2;

    private JwtUtils(){
    }

    public static Key generateKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));}

    private static Date toExpireDate(Date start){
        long expirationMillis =
                    (EXPIRE_DAYS * 24L * 60 * 60 * 1000) +
                    (EXPIRE_HOURS * 60L * 60 * 1000) +
                    (EXPIRE_MINUTES * 60L * 1000);
        return new Date(start.getTime() + expirationMillis);
    }
    public static JwtToken createToken(String username, String role) {
        Date issueAt = new Date(); // Data de criação do token
        Date limit = toExpireDate(issueAt); // Data de expiração do token
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(issueAt)
                .setExpiration(limit)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .claim("role", role)
                .compact();
        return new JwtToken(token);
    }
}
