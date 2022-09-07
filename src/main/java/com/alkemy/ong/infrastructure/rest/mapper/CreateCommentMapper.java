package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.request.CreateCommentRequest;
import com.alkemy.ong.infrastructure.rest.response.CreateCommentResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateCommentMapper {

  public Comment toDomain(CreateCommentRequest request) {
    if (request == null) {
      return null;
    }
    Comment comment = new Comment();
    comment.setBody(request.getBody());
    return comment;
  }

  public CreateCommentResponse toResponse(Comment comment) {
    if (comment == null) {
      return null;
    }
    CreateCommentResponse response = new CreateCommentResponse();
    response.setId(comment.getId());
    response.setBody(comment.getBody());
    response.setBody(comment.getBody());
    response.setCreatedBy(comment.getCreatedBy().getFirstName());
    response.setAssociatedNews(comment.getAssociatedNews().getName());
    response.setCreateTimestamp(comment.getCreateTimestamp());
    return response;
  }
}
