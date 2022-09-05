package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.UserUpdateRequest;
import com.alkemy.ong.infrastructure.rest.response.UserUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdateMapper {

  private final PasswordEncoder passwordEncoder;

  public User toDomain(Identifiable<Long> identifiable, UserUpdateRequest userUpdateRequest) {
    if (userUpdateRequest == null) {
      return null;
    }
    User user = new User();
    user.setId(identifiable.getId());
    user.setFirstName(userUpdateRequest.getFirstName());
    user.setLastName(userUpdateRequest.getLastName());
    user.setImageUrl(userUpdateRequest.getImageUrl());
    user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
    return user;
  }

  public UserUpdateResponse toResponse(User userUpdated) {
    UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
    userUpdateResponse.setFirstName(userUpdated.getFirstName());
    userUpdateResponse.setLastName(userUpdated.getLastName());
    userUpdateResponse.setImageUrl(userUpdated.getImageUrl());
    return userUpdateResponse;
  }
}