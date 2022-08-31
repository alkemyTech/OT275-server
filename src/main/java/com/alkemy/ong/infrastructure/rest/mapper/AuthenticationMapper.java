package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.AuthenticationRequest;
import com.alkemy.ong.infrastructure.rest.response.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {

  public User toDomain(AuthenticationRequest authRequest) {
    if (authRequest == null) {
      return null;
    }
    User user = new User();
    user.setEmail(authRequest.getEmail());
    user.setPassword(authRequest.getPassword());
    return user;
  }

  public AuthenticationResponse toResponse(User user) {
    if (user == null) {
      return null;
    }
    AuthenticationResponse authResponse = new AuthenticationResponse();
    authResponse.setFirstName(user.getFirstName());
    authResponse.setLastName(user.getLastName());
    authResponse.setEmail(user.getEmail());
    authResponse.setImageUrl(user.getImageUrl());
    return authResponse;
  }

}
