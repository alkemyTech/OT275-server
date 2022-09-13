package com.alkemy.ong.application.service.comment.usecase;

import com.alkemy.ong.domain.Comment;

public interface ICreateCommentUseCase {

  Comment create(Comment comment);
}
