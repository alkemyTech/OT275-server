package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.IListUserUseCase;
import com.alkemy.ong.domain.User;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListUserUseCaseService implements IListUserUseCase {

  private final IUserRepository userRepository;

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }
}
