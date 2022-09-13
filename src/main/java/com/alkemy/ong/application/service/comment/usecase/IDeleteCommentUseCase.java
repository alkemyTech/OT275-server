package com.alkemy.ong.application.service.comment.usecase;

import com.alkemy.ong.domain.Identifiable;

public interface IDeleteCommentUseCase {

  void delete(Identifiable<Long> identifiable);

}
