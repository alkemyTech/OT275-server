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

  private IUserRepository userRepository;

  @Override
  public User update(User user) {
    Optional<User> savedUserOptional = userRepository.findById(user::getId);
    if (!userRepository.exists(user::getId) || savedUserOptional.isEmpty())  {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    updateUserData(user, savedUserOptional.get());
    return userRepository.update(savedUserOptional.get());
  }

  private void updateUserData(User user, User savedUser) {
    updateUserAuthenticationData(user, savedUser);
    updateUserPersonalData(user, savedUser);
  }

  private void updateUserAuthenticationData(User user, User savedUser) {
    savedUser.setPassword(user.getPassword());
  }

  private void updateUserPersonalData(User user, User savedUser) {
    savedUser.setFirstName(user.getFirstName());
    savedUser.setLastName(user.getLastName());
    savedUser.setImageUrl(user.getImageUrl());
  }
}
