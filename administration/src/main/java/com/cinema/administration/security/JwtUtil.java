package com.cinema.administration.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final SecretKey JWT_SECRET = Jwts.SIG.HS256.key().build();
    private static final Long JWT_EXPIRATION_TIME = 86400000L;

    public String generateJwtToken(Authentication authentication) {
        Date currentDate = new Date();

        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(currentDate)
                .expiration(new Date(currentDate.getTime() + JWT_EXPIRATION_TIME))
                .signWith(JWT_SECRET)
                .compact();
    }

    public String getJwtTokenSubject(String jwtToken) throws JwtException {
        return Jwts.parser().verifyWith(JWT_SECRET).build().parseSignedClaims(jwtToken).getPayload().getSubject();
    }

}
