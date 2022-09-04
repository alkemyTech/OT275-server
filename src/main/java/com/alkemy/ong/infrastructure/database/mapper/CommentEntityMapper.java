package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    comment.setBody(entity.getBody());
    return comment;
  }

  public List<Comment> toDomain(List<CommentEntity> commentEntities) {
    if (commentEntities.isEmpty()) {
      return Collections.emptyList();
    }
    List<Comment> commentList = new ArrayList<>(commentEntities.size());
    for (CommentEntity commentEntity : commentEntities) {
      commentList.add(toDomain(commentEntity));
    }
    return commentList;
  }
}