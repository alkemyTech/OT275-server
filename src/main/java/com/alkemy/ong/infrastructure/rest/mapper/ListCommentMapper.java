package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.response.ListCommentResponse;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ListCommentMapper {

  private final CommentMapper commentMapper;

  public ListCommentResponse toResponse(List<Comment> comments) {
    if (comments == null || comments.isEmpty()) {
      return new ListCommentResponse(Collections.emptyList());
    }
    return new ListCommentResponse(commentMapper.toResponse(comments));
  }
}
