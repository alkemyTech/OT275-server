package com.alkemy.ong.infrastructure.rest.request.testimonial;

import com.alkemy.ong.infrastructure.rest.request.validation.AlphanumericWithWhiteSpaces;
import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTestimonialRequest {

  @NotEmpty(message = "Name cannot be empty")
  @Size(max = 50, message = "Name must be 50 characters or less.")
  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String name;

  @Size(max = 150, message = "Content must be 150 characters or less.")
  @AlphanumericWithWhiteSpaces(message = "Content must be alphanumeric with white spaces.")
  private String content;

  private String image;

}
