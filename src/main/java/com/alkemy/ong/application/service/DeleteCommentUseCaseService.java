package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.domain.Identifiable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCommentUseCaseService implements IDeleteCommentUseCase {

  private ICommentRepository repository;

  @Override
  public void delete(Identifiable<Long> identifiable) {

  }
}
