package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.NotBlankAlphanumeric;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

  @NotNull(message = "User Id must not be null")
  private Long userId;
  @NotNull(message = "User Id must not be null")
  private Long newsId;
  @NotNull(message = "Comment body mut not be null")
  @NotBlankAlphanumeric(message = "Comment body must only contain letters and numbers, and not be empty")
  private String body;
}
