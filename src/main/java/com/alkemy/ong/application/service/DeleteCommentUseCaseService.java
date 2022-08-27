package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.domain.Identifiable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCommentUseCaseService implements IDeleteCommentUseCase {

  private ICommentRepository repository;

  @Override
  public void delete(Identifiable<Long> identifiable) {
    if(!repository.exists(identifiable)) {
      throw new ObjectNotFound(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Comment"));
    }

    repository.delete(identifiable);
  }
}
