package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.UserAlreadyExists;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUserUseCaseService implements ICreateUserUseCase {
  private final IUserRepository userRepository;

  @Override
  public User add(User user) {
    if (userRepository.find(user.getEmail()).isPresent()) {
      throw new UserAlreadyExists(ErrorMessage.USER_ALREADY_EXISTS.getMessage());
    }
    user.setRole(
        com.alkemy.ong.domain.Role.builder()
            .name(Role.USER.toString())
            .build());
    return userRepository.add(user);
  }
}
