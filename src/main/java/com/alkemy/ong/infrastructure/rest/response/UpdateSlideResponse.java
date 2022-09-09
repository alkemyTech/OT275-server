package com.alkemy.ong.infrastructure.rest.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateSlideResponse {

  private String text;
  private Integer order;
  private String imageUrl;
}
