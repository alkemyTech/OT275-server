package com.alkemy.ong.infrastructure.config.spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class JwtUtilities {

  public String createJwToken(Authentication authenticationResult) {
    return Jwts.builder()
        .setSubject(authenticationResult.getName())
        .claim("roles", authenticationResult.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .setExpiration(Date.valueOf(LocalDate.now().plus(30, ChronoUnit.MINUTES)))
        .signWith(SignatureAlgorithm.HS256, "secret")
        .compact();
  }

  public Claims getClaims(String jwToken) throws JwtException {
    return Jwts.parser()
          .setSigningKey("secret")
          .parseClaimsJws(jwToken).getBody();
  }

  public String getUsername(String jwToken) {
    return getClaims(jwToken).getSubject();
  }

  public List<String> getRoles(String jwToken) {
    return (List<String>) getClaims(jwToken).get("roles");
  }
}
