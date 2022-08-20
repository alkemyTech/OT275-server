package com.alkemy.ong.infrastructure.config.spring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtilities jwtUtilities;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (checkAuthorizationHeader(authorizationHeader, request, response, filterChain)) {
      return;
    }
    try {
      String jwToken = authorizationHeader.replace("Bearer ", "");
      Claims claims = jwtUtilities.getClaims(jwToken);
      String username = claims.getSubject();
      Collection<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(claims);
      Authentication authentication = new UsernamePasswordAuthenticationToken(
          username,
          null,
          grantedAuthorities
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (JwtException e) {
      throw new IllegalStateException("Invalid JW Token");
    }
  }

  private boolean checkAuthorizationHeader(
      String authorizationHeader,
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {
    if (authorizationHeader == null || authorizationHeader.isBlank()
        || !authorizationHeader.startsWith("Bearer")) {
      filterChain.doFilter(request, response);
      return true;
    } else {
      return false;
    }
  }

  private Collection<GrantedAuthority> getGrantedAuthorities(Claims claims) {
    List<String> roleList = (List<String>) claims.get("roles");
    Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    roleList.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));
    return grantedAuthorities;
  }
}
