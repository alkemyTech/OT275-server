package com.alkemy.ong.infrastructure.rest.mapper.user;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.user.RegisterUserRequest;
import com.alkemy.ong.infrastructure.rest.response.user.RegisterUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterUserMapper {

  private final PasswordEncoder passwordEncoder;

  public User toDomain(RegisterUserRequest registerRequest) {
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

  public RegisterUserResponse toResponse(User user) {
    if (user == null) {
      return null;
    }
    RegisterUserResponse registerResponse = new RegisterUserResponse();
    registerResponse.setEmail(user.getEmail());
    registerResponse.setFirstName(user.getFirstName());
    registerResponse.setLastName(user.getLastName());
    registerResponse.setToken(user.getToken());
    return registerResponse;
  }
}
