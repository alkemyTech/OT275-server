package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.exception.OperationNotPermittedException;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.delegate.IOperationAllowed;
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
  private IOperationAllowed operationAllowed;

  @Mock
  private Identifiable<Long> identifiable;

  @BeforeEach
  void setUp() {
    deleteCommentUseCaseService = new DeleteCommentUseCaseService(repository, operationAllowed);
  }

  @Test
  void shouldThrowExceptionWhenCommentDoesNotExist() {
    given(operationAllowed.isAuthorized(identifiable)).willReturn(true);
    given(repository.exists(identifiable)).willReturn(false);

    assertThrows(ObjectNotFoundException.class,
        () -> deleteCommentUseCaseService.delete(identifiable));
  }

  @Test
  void shouldThrowExceptionWhenUserIsNotAuthorized() {
    given(operationAllowed.isAuthorized(identifiable)).willReturn(false);

    assertThrows(OperationNotPermittedException.class,
        () -> deleteCommentUseCaseService.delete(identifiable));
  }

  @Test
  void shouldDeleteCommentWhenUserIsAuthorized() {
    given(repository.exists(identifiable)).willReturn(true);
    given(operationAllowed.isAuthorized(identifiable)).willReturn(true);

    deleteCommentUseCaseService.delete(identifiable);

    verify(repository).exists(identifiable);
    verify(repository).delete(identifiable);
  }

}
