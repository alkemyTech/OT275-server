package com.alkemy.ong.infrastructure.rest.mapper.user;

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

  public UpdateUserResponse toResponse(User user) {
    if (user == null) {
      return null;
    }
    UpdateUserResponse updateUserResponse = new UpdateUserResponse();
    updateUserResponse.setId(user.getId());
    updateUserResponse.setFirstName(user.getFirstName());
    updateUserResponse.setLastName(user.getLastName());
    updateUserResponse.setImageUrl(user.getImageUrl());
    return updateUserResponse;
  }
}