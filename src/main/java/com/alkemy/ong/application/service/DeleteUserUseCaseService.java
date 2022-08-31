package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import com.alkemy.ong.domain.Identifiable;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class DeleteUserUseCaseService implements IDeleteUserUseCase {

  private final IUserRepository userRepository;

  @Transactional
  @Override
  public void delete(Identifiable<Long> identifiable) {
    if (!userRepository.exists(identifiable)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    userRepository.delete(identifiable);
  }
}