package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.Identifiable;

public interface ICreateCommentUseCase {

  Comment create(Comment comment);
}
