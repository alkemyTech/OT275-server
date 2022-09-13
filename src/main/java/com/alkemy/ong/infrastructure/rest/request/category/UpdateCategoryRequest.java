package com.alkemy.ong.infrastructure.rest.request.category;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequest {

  @NotEmpty(message = "Name cannot be empty.")
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String name;
  private String description;
  private String imageUrl;

}
