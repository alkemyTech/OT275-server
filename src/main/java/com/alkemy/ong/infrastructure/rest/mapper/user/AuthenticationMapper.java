package com.alkemy.ong.infrastructure.rest.mapper.user;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.user.AuthenticationRequest;
import com.alkemy.ong.infrastructure.rest.response.user.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

  public User toDomain(AuthenticationRequest authenticationRequest) {
    if (authenticationRequest == null) {
      return null;
    }
    User user = new User();
    user.setEmail(authenticationRequest.getEmail());
    user.setPassword(authenticationRequest.getPassword());
    return user;
  }

  public AuthenticationResponse toResponse(User user) {
    if (user == null) {
      return null;
    }
    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    authenticationResponse.setFirstName(user.getFirstName());
    authenticationResponse.setLastName(user.getLastName());
    authenticationResponse.setEmail(user.getEmail());
    authenticationResponse.setImageUrl(user.getImageUrl());
    authenticationResponse.setToken(user.getToken());
    return authenticationResponse;
  }

}
