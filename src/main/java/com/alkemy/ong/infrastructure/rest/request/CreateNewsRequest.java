package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.AlphanumericWithWhiteSpaces;
import com.alkemy.ong.infrastructure.rest.request.validation.AlphanumericWithoutWhiteSpaces;
import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewsRequest {

  @NotEmpty(message = "Name cannot be empty.")
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  @Size(max = 50, message = "Name must be 50 characters or less.")
  private String name;

  @NotEmpty(message = "Text cannot be empty.")
  @AlphanumericWithWhiteSpaces(message = "Text must be alphanumeric.")
  private String text;

  @NotEmpty(message = "Image cannot be empty.")
  @AlphanumericWithoutWhiteSpaces(
      message = "Image must be alphanumeric without white spaces.")
  private String image;

}
