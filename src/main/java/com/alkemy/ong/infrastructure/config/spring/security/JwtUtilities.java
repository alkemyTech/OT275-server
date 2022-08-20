package com.alkemy.ong.infrastructure.config.spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class JwtUtilities {

  private final String secretKey = Base64.getEncoder().encodeToString("secret".getBytes());

  public String createJwToken(User user) {
    int tokenDuration = 1800000;
    return Jwts.builder()
        .setSubject(user.getUsername())
        .claim("roles", user.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()))
        .setExpiration(new Date(System.currentTimeMillis() + tokenDuration))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public Claims getClaims(String jwToken) throws JwtException {
    return Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(jwToken).getBody();
  }
}
