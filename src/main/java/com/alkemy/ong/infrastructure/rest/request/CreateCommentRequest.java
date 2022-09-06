package com.alkemy.ong.infrastructure.rest.request;

import lombok.Getter;

@Getter
public class CreateCommentRequest {

  private Long userId;
  private Long newsId;
  private String body;
}
