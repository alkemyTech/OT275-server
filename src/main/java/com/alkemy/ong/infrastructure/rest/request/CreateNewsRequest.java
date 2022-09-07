package com.alkemy.ong.infrastructure.rest.request;

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
  @Size(message = "Name must be 50 characters or less.")
  private String name;

  @NotEmpty(message = "Text cannot be empty.")
  @CharactersWithWhiteSpaces(message = "Text must contain only spaces and letters.")
  private String text;

  @NotEmpty(message = "Image must be an URL.")
  private String imageUrl;

}
