package com.alkemy.ong.infrastructure.config.spring.security.filter;

import com.alkemy.ong.infrastructure.config.spring.security.JwtUtilities;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

  private static final String BEARER_PART = "Bearer ";
  @Autowired
  private JwtUtilities jwtUtilities;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (isValid(authorizationHeader)) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      String jwToken = authorizationHeader.replace(BEARER_PART, "");
      Authentication authentication = new UsernamePasswordAuthenticationToken(
          jwtUtilities.extractUsername(jwToken),
          null,
          jwtUtilities.getGrantedAuthorities(jwToken)
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (JwtException e) {
      throw new IllegalStateException("Invalid JW Token");
    }
  }

  private boolean isValid(String authorizationHeader) {
    return authorizationHeader == null || authorizationHeader.isBlank()
        || !authorizationHeader.startsWith(BEARER_PART);
  }

}
