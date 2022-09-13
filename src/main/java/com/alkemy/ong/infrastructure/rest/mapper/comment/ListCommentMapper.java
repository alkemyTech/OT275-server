package com.alkemy.ong.infrastructure.rest.mapper.comment;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.response.common.ListCommentResponse;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ListCommentMapper {

  private final GetCommentMapper getCommentMapper;

  public ListCommentResponse toResponse(List<Comment> comments) {
    if (comments == null || comments.isEmpty()) {
      return new ListCommentResponse(Collections.emptyList());
    }
    return new ListCommentResponse(getCommentMapper.toResponse(comments));
  }
}
