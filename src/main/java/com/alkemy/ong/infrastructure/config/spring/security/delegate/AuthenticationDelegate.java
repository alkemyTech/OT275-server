package com.alkemy.ong.infrastructure.config.spring.security.delegate;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.InvalidCredentialsException;
import com.alkemy.ong.application.service.delegate.IAuthenticationManager;
import com.alkemy.ong.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationDelegate implements IAuthenticationManager {

  private final AuthenticationManager authenticationManager;

  @Override
  public void authenticate(User user) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          user.getEmail(),
          user.getPassword()));
    } catch (Exception e) {
      throw new InvalidCredentialsException(ErrorMessage.INVALID_CREDENTIALS.getMessage());
    }
  }

}
