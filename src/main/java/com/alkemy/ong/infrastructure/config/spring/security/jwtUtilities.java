package com.alkemy.ong.infrastructure.config.spring.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class jwtUtilities {

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
}
