package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentEntityMapper {

  private final UserEntityMapper userEntityMapper;

  public Comment toDomain(CommentEntity entity) {
    Comment comment = new Comment();
    comment.setId(entity.getCommentId());
    comment.setCreatedBy(userEntityMapper.toDomain(entity.getUser()));
    return comment;
  }

}