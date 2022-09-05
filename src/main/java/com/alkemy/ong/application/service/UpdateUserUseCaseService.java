package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.IUpdateUserUseCase;
import com.alkemy.ong.domain.User;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateUserUseCaseService implements IUpdateUserUseCase {

  private final IUserRepository userRepository;

  @Override
  public User update(User user) {
    Optional<User> userOptional = userRepository.findById(user::getId);
    if (!userRepository.exists(user::getId) || userOptional.isEmpty()) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    return userRepository.update(updateValues(user, userOptional.get()));
  }

  private static User updateValues(User user, User updatedUser) {
    updatedUser.setLastName(user.getLastName());
    updatedUser.setFirstName(user.getFirstName());
    updatedUser.setPassword(user.getPassword());
    updatedUser.setImageUrl(user.getImageUrl());
    return updatedUser;
  }
}
