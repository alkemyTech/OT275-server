package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Comment;

public interface ICreateCommentUseCase {

  Comment create(Comment comment);
}
