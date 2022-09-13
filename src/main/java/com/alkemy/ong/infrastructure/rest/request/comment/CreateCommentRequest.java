package com.alkemy.ong.infrastructure.rest.request.comment;

import com.alkemy.ong.infrastructure.rest.request.validation.AlphanumericWithWhiteSpaces;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

  @NotNull(message = "User Id must not be null.")
  private Long userId;
  @NotNull(message = "News Id must not be null.")
  private Long newsId;
  @NotNull(message = "Comment body mut not be null.")
  @AlphanumericWithWhiteSpaces(message = "Comment body content must be alphanumeric.")
  private String body;
}
