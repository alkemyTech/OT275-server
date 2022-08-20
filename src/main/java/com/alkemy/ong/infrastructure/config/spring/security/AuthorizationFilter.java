package com.alkemy.ong.infrastructure.config.spring.security;

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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthorizationFilter extends OncePerRequestFilter {

  @Autowired
  JwtUtilities jwtUtilities;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader("Authorization");
    if (authorizationHeader == null || authorizationHeader.isBlank()
        || !authorizationHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    try {
      String jwToken = authorizationHeader.replace("Bearer ", "");
      String username = jwtUtilities.getUsername(jwToken);
      List<String> roles = jwtUtilities.getRoles(jwToken);
      Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
      roles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));
      Authentication authentication = new UsernamePasswordAuthenticationToken(
          username,
          null,
          grantedAuthorities
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (JwtException e) {
      throw new IllegalStateException("Token cannot be trusted");
    }
  }
}
