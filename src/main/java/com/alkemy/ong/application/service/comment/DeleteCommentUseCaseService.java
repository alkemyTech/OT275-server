package com.alkemy.ong.application.service.comment;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.exception.OperationNotPermittedException;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.comment.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.application.service.delegate.IOperationAllowed;
import com.alkemy.ong.domain.Identifiable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCommentUseCaseService implements IDeleteCommentUseCase {

  private final ICommentRepository commentRepository;
  private final IOperationAllowed operationAllowed;

  @Override
  public void delete(Identifiable<Long> identifiable) {
    if (!operationAllowed.isAuthorized(identifiable)) {
      throw new OperationNotPermittedException(ErrorMessage.OPERATION_NOT_PERMITTED.getMessage());
    }

    if (!commentRepository.exists(identifiable)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Comment"));
    }

    commentRepository.delete(identifiable);
  }
}
