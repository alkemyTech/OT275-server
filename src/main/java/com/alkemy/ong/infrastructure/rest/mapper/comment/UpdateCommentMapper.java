package com.alkemy.ong.infrastructure.rest.mapper.comment;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.request.comment.UpdateCommentRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateCommentMapper extends GetCommentMapper {

  public Comment toDomain(Long id, UpdateCommentRequest request) {
    if (request == null) {
      return null;
    }
    Comment comment = new Comment();
    comment.setId(id);
    comment.setBody(request.getBody());
    return comment;
  }

}