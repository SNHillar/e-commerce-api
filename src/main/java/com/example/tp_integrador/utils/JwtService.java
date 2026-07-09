package com.example.tp_integrador.utils;

import com.example.tp_integrador.dtos.user.UserResponseDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String TOKEN_SECRET;

    @Value("${security.jwt.expiration}")
    private Long TOKEN_EXPIRATION;

    private String generateToken(UserResponseDto userResponseDto, UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", userResponseDto.role());
        claims.put("email", userResponseDto.email());
        return generateToken(claims, userDetails.getPassword());
    }

    private String generateToken(Map<String, Object> claims, String username){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey(){

        if(TOKEN_SECRET == null || TOKEN_SECRET.isEmpty()){
            return Jwts.SIG.HS256.key().build();
        }else {
            return Keys.hmacShaKeyFor(TOKEN_SECRET.getBytes(StandardCharsets.UTF_8));
        }
    }
}
