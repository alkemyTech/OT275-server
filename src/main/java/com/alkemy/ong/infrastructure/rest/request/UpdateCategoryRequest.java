package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhitespaces;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UpdateCategoryRequest {

  @NotEmpty
  @CharactersWithWhitespaces(message = "Category name must contain only spaces and letters.")
  private String name;
  private String description;
  private String imageUrl;

}
