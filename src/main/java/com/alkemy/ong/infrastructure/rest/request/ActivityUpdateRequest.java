package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhitespaces;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class ActivityUpdateRequest {

  @NotEmpty
  @CharactersWithWhitespaces(message = "Activity content must contain only spaces and letters.")
  private String content;

  @NotEmpty(message = "Activity image must be an URL.")
  private String imageUrl;

  @NotEmpty
  @CharactersWithWhitespaces(message = "Activity name must contain only spaces and letters.")
  private String name;

}
