package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.AlphanumericWithWhiteSpaces;
import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateActivityRequest {

  @Size(message = "Name must be between 3 and 20 characters.", min = 3, max = 20)
  @NotEmpty(message = "Name cannot be empty.")
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String name;

  @NotEmpty(message = "Content cannot be empty.")
  @AlphanumericWithWhiteSpaces(message = "Content must be alphanumeric.")
  private String content;

  private String imageUrl;

}
