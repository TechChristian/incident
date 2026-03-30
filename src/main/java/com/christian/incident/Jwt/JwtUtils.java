package com.christian.incident.Jwt;
import io.jsonwebtoken.*;
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
    public static final long EXPIRE_MINUTES = 30;

    private JwtUtils() {
    }

    public static Key generateKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static Date toExpireDate(Date start) {
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

    // -> Extrai informações do Token
    public static Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(generateKey())
                    .build()
                    .parseClaimsJws(refactorTokenBearer(token))
                    .getBody();
        } catch (JwtException ex) {
            log.error("Invalid JWT Token: {}", ex.getMessage());
        }
        return null;
    }

    // -> Obtem username armazenado no Token.
    public static String getUsernameFromToken(String token){
        Claims claims = getClaimsFromToken(token);

        return claims !=null ? claims.getSubject() : null;
    }

    public static boolean isTokenValid(String token){
        return getClaimsFromToken(token) != null;
    }

    // -> Remove o "Bearer" ou seja o prefixo do tipo de Autenticação.
    private static String refactorTokenBearer(String token) {
        if (token.startsWith(JWT_BEARER)) {
            return token.substring(JWT_BEARER.length());
        }
        return token;
    }
}
