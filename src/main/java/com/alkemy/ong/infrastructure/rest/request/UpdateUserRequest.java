package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class UpdateUserRequest {

  @NotEmpty(message = "This field cannot be empty")
  @CharactersWithWhiteSpaces(message = "First name must contain only spaces and letters.")
  private String firstName;

  @NotEmpty(message = "This field cannot be empty")
  @CharactersWithWhiteSpaces(message = "Last name must contain only spaces and letters.")
  private String lastName;

  @Nullable
  private String imageUrl;

  @NotEmpty(message = "This field cannot be empty")
  @Size(min = 8, max = 16, message = "The password must be between 8 and 16 characters long.")
  private String password;

}
