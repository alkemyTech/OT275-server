package com.alkemy.ong.application.service.comment;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.exception.OperationNotPermittedException;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.comment.usecase.IUpdateCommentUseCase;
import com.alkemy.ong.application.service.delegate.IOperationAllowed;
import com.alkemy.ong.domain.Comment;
import java.util.Optional;
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
    Optional<Comment> commentFromRepository = commentRepository.find(comment::getId);
    if (commentFromRepository.isEmpty()) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Comment"));
    }
    return update(commentFromRepository.get(), comment);
  }

  private Comment update(Comment commentFromRepository, Comment comment) {
    commentFromRepository.setBody(comment.getBody());
    return commentRepository.update(commentFromRepository);
  }
}
