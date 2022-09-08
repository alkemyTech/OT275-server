package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSlideRequest {

  @NotNull(message = "This field cannot be null!")
  private String text;

  @NotNull(message = "This field cannot be null!")
  @Min(value = 1, message = "Slide position must be greater or equal to 1.")
  private Integer order;

}