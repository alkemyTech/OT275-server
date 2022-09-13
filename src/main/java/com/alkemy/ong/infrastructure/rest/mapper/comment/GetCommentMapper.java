package com.alkemy.ong.infrastructure.rest.mapper.comment;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.response.comment.GetCommentResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetCommentMapper {

  public GetCommentResponse toResponse(Comment comment) {
    if (comment == null) {
      return null;
    }
    GetCommentResponse getCommentResponse = new GetCommentResponse();
    getCommentResponse.setBody(comment.getBody());
    return getCommentResponse;
  }

  public List<GetCommentResponse> toResponse(List<Comment> comments) {
    if (comments.isEmpty()) {
      return Collections.emptyList();
    }
    List<GetCommentResponse> getCommentRespons = new ArrayList<>(comments.size());
    for (Comment comment : comments) {
      getCommentRespons.add(toResponse(comment));
    }
    return getCommentRespons;
  }

}