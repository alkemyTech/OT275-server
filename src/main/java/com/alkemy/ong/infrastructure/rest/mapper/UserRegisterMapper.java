package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.UserRegisterRequest;
import com.alkemy.ong.infrastructure.rest.response.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegisterMapper {

  private final PasswordEncoder passwordEncoder;

  public User toDomain(UserRegisterRequest registerRequest) {
    if (registerRequest == null) {
      return null;
    }
    User user = new User();
    user.setEmail(registerRequest.getEmail());
    user.setFirstName(registerRequest.getFirstName());
    user.setLastName(registerRequest.getLastName());
    user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
    return user;
  }

  public UserRegisterResponse toResponse(User user) {
    if (user == null) {
      return null;
    }
    UserRegisterResponse registerResponse = new UserRegisterResponse();
    registerResponse.setEmail(user.getEmail());
    registerResponse.setFirstName(user.getFirstName());
    registerResponse.setLastName(user.getLastName());
    return registerResponse;
  }
}
