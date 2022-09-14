package com.alkemy.ong.application.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.exception.OperationNotPermittedException;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.comment.UpdateCommentUseCase;
import com.alkemy.ong.application.service.delegate.IOperationAllowed;
import com.alkemy.ong.domain.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateCommentUseCaseTest {

  private UpdateCommentUseCase updateCommentUseCase;

  @Mock
  private ICommentRepository commentRepository;
  @Mock
  private IOperationAllowed operationAllowed;

  @Mock
  private Comment comment;

  @BeforeEach
  void setUp() {
    updateCommentUseCase = new UpdateCommentUseCase(commentRepository, operationAllowed);
  }

  @Test
  void shouldThrowExceptionWhenNotAuthorized() {

    given(operationAllowed.isAuthorized(any())).willReturn(false);

    assertThrows(OperationNotPermittedException.class,
        () -> updateCommentUseCase.update(comment));
  }

  @Test
  void shouldThrowExceptionWhenCommentDoesNotExist() {

    given(operationAllowed.isAuthorized(any())).willReturn(true);
    given(commentRepository.exists(any())).willReturn(false);

    assertThrows(ObjectNotFoundException.class, () -> updateCommentUseCase.update(comment));
  }

  @Test
  void shouldUpdateCommentWhenIsAuthorized() {

    given(operationAllowed.isAuthorized(any())).willReturn(true);
    given(commentRepository.exists(any())).willReturn(true);
    given(commentRepository.update(comment)).willReturn(comment);

    updateCommentUseCase.update(comment);

    verify(commentRepository).exists(any());
    verify(commentRepository).update(comment);
  }


}