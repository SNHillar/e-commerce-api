package com.example.tp_integrador.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import java.util.Map;
import java.util.function.Function;

import static aQute.bnd.annotation.headers.Category.security;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private String TOKEN_SECRET;

    @Value("${security.jwt.expiration}")
    private Long TOKEN_EXPIRATION;

    @Value("${security.jwt.refresh.expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = Map.of("authorities", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList()
        );
        return generateToken(claims, userDetails.getUsername());
    }

    private String generateToken(Map<String, Object> claims, String subjet){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subjet)
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

    private Claims getClaimsFromToken(String token){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims;
        } catch (ExpiredJwtException e){
            return e.getClaims();
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e){
            throw new RuntimeException("Unsupported Jwt", e);
        }
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        Claims allClaims = getClaimsFromToken(token);
        return claimsResolver.apply(allClaims);
    }

    public String getUsernameFromToken(String token){
        return getClaim(token, Claims::getSubject);
    }

    public Date  getExpirationDateFromToken(String token){
        return getClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        return getExpirationDateFromToken(token).before(new Date());
    }

    public boolean canBeTokenRenewed(String token){
        return getExpirationDateFromToken(token).before(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION));
    }

    public String renewToken(String token, UserDetails userDetails){
        if (!canBeTokenRenewed(token)) {
            throw new RuntimeException("Token is not valid");
        }
        return generateToken(userDetails);
    }

    public boolean isValidToken(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername());
    }
}
