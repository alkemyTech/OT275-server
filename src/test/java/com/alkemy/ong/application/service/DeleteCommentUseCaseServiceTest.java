package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.application.exception.OperationNotPermitted;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.usecase.IAuthorization;
import com.alkemy.ong.domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteCommentUseCaseServiceTest {

  private DeleteCommentUseCaseService deleteCommentUseCaseService;

  @Mock
  private ICommentRepository repository;

  @Mock
  private IAuthorization authorization;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setUp() {
    deleteCommentUseCaseService = new DeleteCommentUseCaseService(repository, authorization);
  }

  @Test
  void shouldThrowExceptionWhenCommentDoesNotExist() {
    given(repository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFound.class, () -> deleteCommentUseCaseService.delete(identifiable));
  }

  @Test
  void shouldDeleteCommentWhenUserExist() {
    given(repository.exists(identifiable)).willReturn(true);
    when(authorization.isAuthorized(identifiable)).thenReturn(true);

    deleteCommentUseCaseService.delete(identifiable);

    verify(repository).exists(identifiable);
    verify(repository).delete(identifiable);
  }

  @Test
  void shouldThrowExceptionWhenUserIsNotAuthorized() {
    given(repository.exists(identifiable)).willReturn(true);
    when(authorization.isAuthorized(identifiable)).thenReturn(false);

    assertThrows(OperationNotPermitted.class,
        () -> deleteCommentUseCaseService.delete(identifiable));
  }

  @Test
  void shouldDeleteCommentWhenUserIsAuthorized() {
    given(repository.exists(identifiable)).willReturn(true);
    when(authorization.isAuthorized(identifiable)).thenReturn(true);

    deleteCommentUseCaseService.delete(identifiable);

    verify(repository).exists(identifiable);
    verify(repository).delete(identifiable);
  }

}
