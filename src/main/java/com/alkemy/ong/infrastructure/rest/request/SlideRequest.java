package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlideRequest {

  private String text;
  private Integer order;
  @NotBlank(message = "File must not be empty and must be base64 encoded")
  private String base64FileEncoded;

}
