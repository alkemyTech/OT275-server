package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.UserAlreadyExists;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.domain.Role;
import com.alkemy.ong.domain.User;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseServiceTest {

  private static final String EMAIL = "ricky@fort.com";
  private static final String ROLE = "ROLE_USER";

  private ICreateUserUseCase userService;

  @Mock
  private IUserRepository userRepository;

  @BeforeEach
  void setup() {
    userService = new CreateUserUseCaseService(userRepository);
  }

  @Test
  void shouldThrowExceptionWhenUserAlreadyExist() {
    User user = new User();
    user.setEmail(EMAIL);

    given(userRepository.find(EMAIL)).willReturn(Optional.ofNullable(user));

    assertThrows(UserAlreadyExists.class, () -> userService.add(user));
  }

  @Test
  void shouldSaveUserWhenUserDoesNotExist() {
    given(userRepository.find(EMAIL)).willReturn(Optional.empty());

    Role role = new Role();
    role.setName(ROLE);

    User user = new User();
    user.setEmail(EMAIL);
    user.setRole(role);

    userService.add(user);

    verify(userRepository).find(EMAIL);
    verify(userRepository).add(user);
  }
}
