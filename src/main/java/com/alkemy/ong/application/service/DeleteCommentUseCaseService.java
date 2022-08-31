package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.application.exception.OperationNotPermitted;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.application.service.delegate.IOperationAllowed;
import com.alkemy.ong.domain.Identifiable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCommentUseCaseService implements IDeleteCommentUseCase {

  private ICommentRepository commentRepository;

  private IOperationAllowed operationAllowed;

  @Override
  public void delete(Identifiable<Long> identifiable) {
    if (!operationAllowed.isAuthorized(identifiable)) {
      throw new OperationNotPermitted(ErrorMessage.OPERATION_NOT_PERMITTED.getMessage());
    }

    if (!commentRepository.exists(identifiable)) {
      throw new ObjectNotFound(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Comment"));
    }

    commentRepository.delete(identifiable);
  }
}
