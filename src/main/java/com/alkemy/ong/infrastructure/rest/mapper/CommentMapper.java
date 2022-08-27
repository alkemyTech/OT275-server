package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import com.alkemy.ong.infrastructure.rest.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper implements ICommentMapper{

  private final UserMapper userMapper;

  @Override
  public Comment toDomain(CommentEntity entity) {
    Comment comment = new Comment();

    comment.setUser(userMapper.toDomain(entity.getUser()));

    return comment;
  }

  @Override
  public CommentResponse fromDomain(Comment comment) {
    CommentResponse commentResponse = new CommentResponse();

    commentResponse.setUser();
  }
}
