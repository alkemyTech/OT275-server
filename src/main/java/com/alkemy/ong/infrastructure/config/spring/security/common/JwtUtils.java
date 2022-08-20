package com.alkemy.ong.infrastructure.config.spring.security.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUtils {

  private static final String SECRET_KEY = Base64.getEncoder().encodeToString("secret".getBytes());
  private static final String BEARER_PART = "Bearer ";
  private static final String EMPTY = "";
  private static final String ROLES_CLAIM = "roles";

  private JwtUtils() {
  }

  public static String create(UserDetails userDetails) {
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

  public static JWT extract(String authorizationHeader) {
    return JWT.extract(authorizationHeader);
  }

  public static class JWT {

    private final String username;

    private final List<GrantedAuthority> grantedAuthorities;

    private JWT(String token) {
      Claims claims = extractAllClaims(token);
      this.username = claims.getSubject();
      this.grantedAuthorities = buildGrantedAuthorities(claims);
    }

    public static JWT extract(String authorizationHeader) {
      return new JWT(getTokenFrom(authorizationHeader));
    }

    public String getUsername() {
      return username;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
      return grantedAuthorities;
    }

    private List<GrantedAuthority> buildGrantedAuthorities(Claims claims) {
      return AuthorityUtils.commaSeparatedStringToAuthorityList(
          Objects.toString(claims.get(ROLES_CLAIM)));
    }

    private static String getTokenFrom(String authorizationHeader) {
      return authorizationHeader.replace(BEARER_PART, EMPTY);
    }

    private static Claims extractAllClaims(String token) {
      return Jwts.parser()
          .setSigningKey(SECRET_KEY)
          .parseClaimsJws(token)
          .getBody();
    }

  }
}
