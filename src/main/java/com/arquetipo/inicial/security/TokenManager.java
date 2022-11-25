package com.arquetipo.inicial.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class TokenManager implements Serializable {

    @Value("${jwt.secretkey}")
    private String jwtSecret;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        if (expiration != null) {
            return expiration.before(new Date());
        } else {
            return true;
        }
    }


    public String generateToken(UserDetails userDetails, String authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Authorities", authorities);
        if (!userDetails.getAuthorities().isEmpty()) {
            userDetails.getAuthorities().forEach(a -> {
                claims.put(a.getAuthority(), a.getAuthority());
            });
        }
        return doGenerateToken(claims, authorities);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))

                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()));
    }

}
