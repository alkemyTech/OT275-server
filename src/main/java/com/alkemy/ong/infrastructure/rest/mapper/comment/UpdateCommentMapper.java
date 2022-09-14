package com.alkemy.ong.infrastructure.rest.mapper.comment;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.request.comment.UpdateCommentRequest;
import com.alkemy.ong.infrastructure.rest.response.comment.CommentResponse;
import org.springframework.stereotype.Component;

@Component
public class UpdateCommentMapper {

  public Comment toDomain(Long id, UpdateCommentRequest request) {
    if (request == null) {
      return null;
    }
    Comment comment = new Comment();
    comment.setId(id);
    comment.setBody(request.getBody());
    return comment;
  }

  public CommentResponse toResponse(Comment comment) {
    if (comment == null) {
      return null;
    }
    CommentResponse commentResponse = new CommentResponse();
    commentResponse.setBody(comment.getBody());
    return commentResponse;
  }

}