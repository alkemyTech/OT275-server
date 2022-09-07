package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.NotBlankAlphanumeric;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCommentRequest {
  @NotNull
  private Long userId;
  @NotNull
  private Long newsId;
  @NotBlankAlphanumeric
  private String body;
}
