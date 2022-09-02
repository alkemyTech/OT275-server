package com.alkemy.ong.infrastructure.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideWithTextResponse extends SlideResponse {

  private String text;

  public SlideWithTextResponse(String imageUrl, Integer order, String text) {
    super(imageUrl, order);
    this.text = text;
  }
}
