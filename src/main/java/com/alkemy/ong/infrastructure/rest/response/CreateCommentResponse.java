package com.alkemy.ong.infrastructure.rest.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateCommentResponse {
  private Long id;
  private String body;
  private String createdBy;
  private String associatedNews;
  private Long createTimestamp;
}
