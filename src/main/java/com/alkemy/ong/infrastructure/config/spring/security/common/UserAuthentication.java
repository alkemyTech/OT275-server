package com.alkemy.ong.infrastructure.config.spring.security.common;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.InvalidCredentials;
import com.alkemy.ong.application.service.delegate.IAuthenticateUser;
import com.alkemy.ong.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserAuthentication implements IAuthenticateUser {

  private AuthenticationManager authenticationManager;

  @Override
  public void authenticate(User user) {
    UserDetails userDetails;
    try {
      Authentication auth = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              user.getEmail(),
              user.getPassword()
          )
      );
      userDetails = (UserDetails) auth.getPrincipal();
    } catch (Exception e) {
      throw new InvalidCredentials(ErrorMessage.INVALID_CREDENTIALS.getMessage());
    }
    final String token = JwtUtils.create(userDetails);
    System.out.println(token);
  }

}
