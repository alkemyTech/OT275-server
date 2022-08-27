package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {

  private final UserMapper userMapper;

  public CommentResponse toResponse(Comment comment) {
    CommentResponse commentResponse = new CommentResponse();

    commentResponse.setUser(userMapper.toResponse(comment.getUser()));

    return commentResponse;
  }
}
