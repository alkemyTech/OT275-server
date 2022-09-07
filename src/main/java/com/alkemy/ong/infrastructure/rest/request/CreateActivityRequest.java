package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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

  @Size(message = "name must be between 3 and 20 characters", min = 3, max = 20)
  @NotEmpty(message = "Name cannot be empty.")
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String name;

  @NotEmpty(message = "Content cannot be empty.")
  @Pattern(regexp = "^[0-9a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$",
      message = "Content has disallowed characters")
  private String content;

  private String imageUrl;

}
