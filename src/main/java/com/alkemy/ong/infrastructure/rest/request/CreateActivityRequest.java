package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateActivityRequest {

  @NotEmpty(message = "Name cannot be empty.")
  @Size(max = 10)
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String name;

  @NotEmpty(message = "Name cannot be empty.")
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String content;

  private String imageUrl;

}
