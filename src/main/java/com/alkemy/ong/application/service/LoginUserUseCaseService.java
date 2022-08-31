package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.delegate.IAuthenticateUser;
import com.alkemy.ong.application.service.usecase.ILoginUserUseCase;
import com.alkemy.ong.domain.User;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginUserUseCaseService implements ILoginUserUseCase {

  private IUserRepository userRepository;
  private IAuthenticateUser authenticateUser;

  @Override
  public User login(User user) {
    authenticateUser.authenticate(user);
    return findBy(user.getEmail());
  }

  private User findBy(String email) {
    Optional<User> user = userRepository.findBy(email);
    if (user.isEmpty()) {
      throw new ObjectNotFound(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    return user.get();
  }

}
