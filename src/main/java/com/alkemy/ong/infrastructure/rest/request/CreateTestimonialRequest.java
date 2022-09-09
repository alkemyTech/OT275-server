package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateTestimonialRequest {


  @NotEmpty(message = "name must not be empty.")
  @NotNull(message = "name must not be null.")
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  @Size(message = "Name must shorter than 50 characters.", max = 50)
  private String name;


  @Size(message = "Content must shorter than 150 characters.", max = 150)
  private String content;


  private String imageUrl;
}
