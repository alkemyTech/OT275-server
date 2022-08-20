package com.alkemy.ong.infrastructure.config.spring.security.filter;

import com.alkemy.ong.infrastructure.config.spring.security.JwtUtilities;
import com.alkemy.ong.infrastructure.config.spring.security.JwtUtilities.JWT;
import io.jsonwebtoken.JwtException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

  private static final String BEARER_PART = "Bearer ";
  private static final Object CREDENTIALS = null;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (!isValid(authorizationHeader)) {
      SecurityContextHolder.clearContext();
      filterChain.doFilter(request, response);
      return;
    }

    try {
      JWT jwt = JwtUtilities.extract(authorizationHeader);
      Authentication authentication = new UsernamePasswordAuthenticationToken(
          jwt.getUsername(),
          CREDENTIALS,
          jwt.getGrantedAuthorities()
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (JwtException e) {
      throw new IllegalStateException("Invalid JWT signature.");
    }
  }

  private boolean isValid(String authorizationHeader) {
    return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(BEARER_PART);
  }

}
