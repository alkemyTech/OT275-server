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
    if (userRepository.findBy(user::getId) == null) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    return userRepository.update(user);
  }

}
