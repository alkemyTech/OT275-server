package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.IUpdateUserUseCase;
import com.alkemy.ong.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateUserUseCaseService implements IUpdateUserUseCase {

  private final IUserRepository userRepository;

  @Override
  public User update(User user) {
    User savedUser = userRepository.findBy(user::getId);
    if (savedUser == null) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    return update(user, savedUser);
  }

  private User update(User user, User savedUser) {
    savedUser.setFirstName(user.getFirstName());
    savedUser.setLastName(user.getLastName());
    savedUser.setImageUrl(user.getImageUrl());
    savedUser.setPassword(user.getPassword());
    return userRepository.update(savedUser);
  }

}
