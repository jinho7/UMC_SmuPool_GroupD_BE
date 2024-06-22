package com.umc.smupool.global.Security.util;

import com.umc.smupool.domain.member.exception.AuthErrorCode;
import com.umc.smupool.domain.member.exception.handler.AuthHandler;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey secretKey;
    private final long accessTokenValidityMilliseconds;

    public JWTUtil(
            @Value("${spring.jwt.secret}") final String secretKey,
            @Value("${spring.jwt.access-token-time}") final long accessTokenValidityMilliseconds) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValidityMilliseconds = accessTokenValidityMilliseconds;
    }

    public String createAccessToken(String studentId, String role) {
        return createToken(String.valueOf(studentId), role, accessTokenValidityMilliseconds);
    }

    private String createToken(String studentId, String role, long validityMilliseconds) {
        Claims claims = Jwts.claims();
        claims.put("studentId", studentId);
        claims.put("role", role);

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(validityMilliseconds / 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getStudentId(String token) {
        return getClaims(token).getBody().get("studentId", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Jws<Claims> claims = getClaims(token);
            Date expiredDate = claims.getBody().getExpiration();
            Date now = new Date();
            return expiredDate.after(now);
        } catch (ExpiredJwtException e) {
            throw new AuthHandler(AuthErrorCode._AUTH_EXPIRE_TOKEN);
        } catch (SignatureException
                 | SecurityException
                 | IllegalArgumentException
                 | MalformedJwtException
                 | UnsupportedJwtException e) {
            throw new AuthHandler(AuthErrorCode._AUTH_INVALID_TOKEN);
        }
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
    }
}
