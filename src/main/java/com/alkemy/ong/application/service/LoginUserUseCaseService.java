package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.delegate.IAuthenticationManager;
import com.alkemy.ong.application.service.usecase.ILoginUserUseCase;
import com.alkemy.ong.domain.User;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginUserUseCaseService implements ILoginUserUseCase {

  private final IUserRepository userRepository;
  private final IAuthenticationManager authenticationManager;

  @Override
  public User login(User user) {
    authenticationManager.authenticate(user);
    return findBy(user.getEmail());
  }

  private User findBy(String email) {
    Optional<User> user = userRepository.findBy(email);
    if (user.isEmpty()) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    return user.get();
  }

}
