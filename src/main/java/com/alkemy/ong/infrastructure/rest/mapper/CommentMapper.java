package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.response.CommentResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public CommentResponse toResponse(Comment comment) {
    if (comment == null) {
      return null;
    }
    CommentResponse commentResponse = new CommentResponse();
    commentResponse.setBody(comment.getBody());
    return commentResponse;
  }

  public List<CommentResponse> toResponse(List<Comment> comments) {
    if (comments.isEmpty()) {
      return Collections.emptyList();
    }
    List<CommentResponse> commentResponses = new ArrayList<>(comments.size());
    for (Comment comment : comments) {
      commentResponses.add(toResponse(comment));
    }
    return commentResponses;
  }
}
