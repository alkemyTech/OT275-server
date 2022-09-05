package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
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

  @NotEmpty(message = "Name cannot be empty.")
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String name;
  private String description;
  private String image;

}
