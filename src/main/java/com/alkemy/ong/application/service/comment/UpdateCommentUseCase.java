package com.alkemy.ong.application.service.comment;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.exception.OperationNotPermittedException;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.comment.usecase.IUpdateCommentUseCase;
import com.alkemy.ong.application.service.delegate.IOperationAllowed;
import com.alkemy.ong.domain.Comment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateCommentUseCase implements IUpdateCommentUseCase {

  private final ICommentRepository commentRepository;
  private final IOperationAllowed operationAllowed;

  @Override
  public Comment update(Comment comment) {
    if (!operationAllowed.isAuthorized(comment::getId)) {
      throw new OperationNotPermittedException(ErrorMessage.OPERATION_NOT_PERMITTED.getMessage());
    }
    if (!commentRepository.exists(comment::getId)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Comment"));
    }
    return commentRepository.update(comment);
  }
}
