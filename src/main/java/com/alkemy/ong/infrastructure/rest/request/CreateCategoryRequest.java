package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhitespaces;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCategoryRequest {

  @NotEmpty(message = "Category name cannot be empty")
  @CharactersWithWhitespaces(message = "Category name must contain only spaces and letters.")
  private String name;
  private String description;
  private String image;

}
