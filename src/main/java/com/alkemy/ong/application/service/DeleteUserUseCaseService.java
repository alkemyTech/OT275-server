package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import com.alkemy.ong.domain.Identifiable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteUserUseCaseService implements IDeleteUserUseCase {

  private final IUserRepository userRepository;

  @Override
  public void delete(Identifiable<Long> identifiable) {

  }
}