package com.alkemy.ong.infrastructure.config.spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtilities {

  private static final String SECRET_KEY = Base64.getEncoder().encodeToString("secret".getBytes());
  private static final String BEARER_PART = "Bearer ";
  private static final String EMPTY = "";
  private static final String ROLES_CLAIM = "roles";

  public String createToken(UserDetails userDetails) {
    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .claim(ROLES_CLAIM, userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList()))
        .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
        .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(90).toInstant()))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  public String extractUsername(String authorizationHeader) {
    return extractClaim(authorizationHeader, Claims::getSubject);
  }

  public List<GrantedAuthority> getGrantedAuthorities(String authorizationHeader) {
    return AuthorityUtils.commaSeparatedStringToAuthorityList(
        Objects.toString(extractAllClaims(authorizationHeader).get(ROLES_CLAIM)));
  }

  private String getTokenFrom(String authorizationHeader) {
    return authorizationHeader.replace(BEARER_PART, EMPTY);
  }

  private <T> T extractClaim(String authorizationHeader, Function<Claims, T> claimsResolver) {
    Claims claims = extractAllClaims(authorizationHeader);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String authorizationHeader) {
    return Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(getTokenFrom(authorizationHeader))
        .getBody();
  }

}
