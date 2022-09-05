package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.UpdateUserRequest;
import com.alkemy.ong.infrastructure.rest.response.UpdateUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateUserMapper {

  private final PasswordEncoder passwordEncoder;

  public User toDomain(Identifiable<Long> identifiable, UpdateUserRequest updateUserRequest) {
    if (updateUserRequest == null) {
      return null;
    }
    User user = new User();
    user.setId(identifiable.getId());
    user.setFirstName(updateUserRequest.getFirstName());
    user.setLastName(updateUserRequest.getLastName());
    user.setImageUrl(updateUserRequest.getImageUrl());
    user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
    return user;
  }

  public UpdateUserResponse toResponse(User userUpdated) {
    UpdateUserResponse updateUserResponse = new UpdateUserResponse();
    updateUserResponse.setId(userUpdated.getId());
    updateUserResponse.setFirstName(userUpdated.getFirstName());
    updateUserResponse.setLastName(userUpdated.getLastName());
    updateUserResponse.setImageUrl(userUpdated.getImageUrl());
    return updateUserResponse;
  }
}