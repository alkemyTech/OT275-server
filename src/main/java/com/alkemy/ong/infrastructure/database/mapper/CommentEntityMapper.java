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
  private final NewsEntityMapper newsEntityMapper;

  public Comment toDomain(CommentEntity commentEntity) {
    if (commentEntity == null) {
      return null;
    }
    Comment comment = new Comment();
    comment.setId(commentEntity.getCommentId());
    comment.setBody(commentEntity.getBody());
    comment.setCreatedBy(userEntityMapper.toDomain(commentEntity.getUser()));
    comment.setAssociatedNews(newsEntityMapper.toDomain(commentEntity.getNews()));
    comment.setCreateTimestamp(commentEntity.getCreateTimestamp());
    return comment;
  }

  public List<Comment> toDomain(List<CommentEntity> commentEntities) {
    if (commentEntities == null || commentEntities.isEmpty()) {
      return Collections.emptyList();
    }
    List<Comment> comments = new ArrayList<>(commentEntities.size());
    for (CommentEntity commentEntity : commentEntities) {
      comments.add(toDomain(commentEntity));
    }
    return comments;
  }

  public CommentEntity toEntity(Comment comment) {
    if (comment == null) {
      return null;
    }
    CommentEntity entity = new CommentEntity();
    entity.setCommentId(comment.getId());
    entity.setUser(userEntityMapper.toEntity(comment.getCreatedBy()));
    entity.setBody(comment.getBody());
    entity.setNews(newsEntityMapper.toEntity(comment.getAssociatedNews()));
    return entity;
  }

}