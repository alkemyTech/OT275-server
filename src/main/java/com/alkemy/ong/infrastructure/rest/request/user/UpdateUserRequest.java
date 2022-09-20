package com.alkemy.ong.infrastructure.rest.request.user;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Setter
@Getter
public class UpdateUserRequest {

  @NotEmpty(message = "First name cannot be empty.")
  @CharactersWithWhiteSpaces(message = "First name must contain only spaces and letters.")
  private String firstName;

  @NotEmpty(message = "Last name cannot be empty.")
  @CharactersWithWhiteSpaces(message = "Last name must contain only spaces and letters.")
  private String lastName;

  @Nullable
  private String imageUrl;

  @NotEmpty(message = "Password cannot be empty")
  @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters long.")
  private String password;

}
