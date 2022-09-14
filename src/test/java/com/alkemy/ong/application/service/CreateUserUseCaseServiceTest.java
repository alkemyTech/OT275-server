package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.UserAlreadyExistsException;
import com.alkemy.ong.application.repository.IRoleRepository;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.application.service.user.CreateUserUseCaseService;
import com.alkemy.ong.application.service.user.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.util.mail.IMailSender;
import com.alkemy.ong.domain.Role;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.database.repository.OrganizationRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseServiceTest {

  private static final String EMAIL = "ricky@fort.com";
  private static final String ROLE = "ROLE_USER";

  private ICreateUserUseCase createUserUseCase;

  @Mock
  private IUserRepository userRepository;

  @Mock
  private IRoleRepository roleRepository;

  @Mock
  private OrganizationRepository organizationRepository;

  @Mock
  private IMailSender mailSender;

  @BeforeEach
  void setup() {
    createUserUseCase = new CreateUserUseCaseService(userRepository, roleRepository,
        organizationRepository, mailSender);
  }

  @Test
  void shouldThrowExceptionWhenUserAlreadyExist() {
    User user = new User();
    user.setEmail(EMAIL);

    given(userRepository.findBy(EMAIL)).willReturn(Optional.of(user));

    assertThrows(UserAlreadyExistsException.class, () -> createUserUseCase.add(user));
  }

  @Test
  void shouldSaveUserWhenUserDoesNotExist() {
    Role role = new Role();
    role.setName(ROLE);

    given(userRepository.findBy(EMAIL)).willReturn(Optional.empty());
    given(roleRepository.findRoleUser()).willReturn(role);

    User user = new User();
    user.setEmail(EMAIL);

    createUserUseCase.add(user);

    verify(userRepository).findBy(EMAIL);
    verify(roleRepository).findRoleUser();
    verify(userRepository).add(user);
  }
}
