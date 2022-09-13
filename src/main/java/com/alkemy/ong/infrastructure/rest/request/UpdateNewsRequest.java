package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.AlphanumericWithWhiteSpaces;
import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNewsRequest {

  @Size(message = "Name must be 50 characters.", max = 50)
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String name;

  @AlphanumericWithWhiteSpaces(message = "Content must be alphanumeric.")
  private String content;
  private String imageUrl;

}
