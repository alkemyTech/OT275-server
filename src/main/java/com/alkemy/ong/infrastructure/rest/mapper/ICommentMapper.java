package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import com.alkemy.ong.infrastructure.rest.response.CommentResponse;

public interface ICommentMapper {

  Comment toDomain(CommentEntity entity);
  CommentResponse fromDomain(Comment comment);

}
