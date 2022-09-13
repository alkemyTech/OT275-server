package com.alkemy.ong.application.service.user;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.user.usecase.IGetUserUseCase;
import com.alkemy.ong.domain.User;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetUserUseCaseService implements IGetUserUseCase {

  private final IUserRepository userRepository;

  @Override
  public User get(String email) {
    Optional<User> user = userRepository.findBy(email);
    if (user.isEmpty()) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    return user.get();
  }

}
