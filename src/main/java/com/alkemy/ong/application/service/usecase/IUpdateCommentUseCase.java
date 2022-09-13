package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Comment;

public interface IUpdateCommentUseCase {

  Comment update(Comment toDomain);
}
