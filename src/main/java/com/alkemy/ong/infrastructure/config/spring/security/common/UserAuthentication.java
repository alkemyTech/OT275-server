package com.alkemy.ong.infrastructure.config.spring.security.common;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.InvalidCredentials;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IUserSpringRepository;
import com.alkemy.ong.infrastructure.rest.request.AuthenticationRequest;
import com.alkemy.ong.infrastructure.rest.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserAuthentication {

  private final IUserSpringRepository userSpringRepository;

  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse login(AuthenticationRequest authRequest) {
    UserEntity user = userSpringRepository.findByUsername(authRequest.getEmail());
    if (user.getEmail() == null) {
      throw new InvalidCredentials(ErrorMessage.INVALID_CREDENTIALS.getMessage());
    }
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            authRequest.getEmail(),
            authRequest.getPassword()
        )
    );
    return new AuthenticationResponse(
        user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getImageUrl()
    );
  }

}
