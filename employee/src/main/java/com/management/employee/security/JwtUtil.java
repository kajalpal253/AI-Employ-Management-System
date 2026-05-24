package com.management.employee.security;

import java.util.Date;

import org.springframework.stereotype.Component;
import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil{

    private final String SECRET_KEY="mysecretkeymysecretkeymysecretkey12345";
    private final Key key =
            Keys.hmacShaKeyFor(SECRET_KEY.getBytes());


    public String generateToken(String username){
        return Jwts.builder()
                   .setSubject(username)
                   .setIssuedAt(new Date())
                   .setExpiration(
                    new Date(System.currentTimeMillis()+1000*60*60)
                   )

                .signWith(key, SignatureAlgorithm.HS256)
                   .compact();
    }

    public String extractUsername(String  token){
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token,String username){

        String extractedUsername =extractUsername(token);

        return extractedUsername.equals(username) 
                        && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractClaims(token)
               .getExpiration()
               .before(new Date());
    }

    private Claims extractClaims(String token){
        return Jwts.parserBuilder()
                  .setSigningKey(key)
                  .build()
                  .parseClaimsJws(token)
                  .getBody();
    }
}