package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseServiceTest {

  private DeleteUserUseCaseService deleteUserUseCaseService;

  @Mock
  private IUserRepository userRepository;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setup() {
    deleteUserUseCaseService = new DeleteUserUseCaseService(userRepository);
  }

  @Test
  void shouldThrowExceptionWhenUserDoesNotExist() {
    given(userRepository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFoundException.class, () -> deleteUserUseCaseService.delete(identifiable));
  }

  @Test
  void shouldDeleteUserWhenUserExist() {
    given(userRepository.exists(identifiable)).willReturn(true);

    deleteUserUseCaseService.delete(identifiable);

    verify(userRepository).exists(identifiable);
    verify(userRepository).delete(identifiable);
  }

}