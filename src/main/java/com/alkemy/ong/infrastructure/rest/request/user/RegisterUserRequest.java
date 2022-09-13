package com.alkemy.ong.infrastructure.rest.request.user;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterUserRequest {

  @CharactersWithWhiteSpaces(message = "First name must contain only spaces and letters.")
  private String firstName;

  @CharactersWithWhiteSpaces(message = "Last name must contain only spaces and letters.")
  private String lastName;

  @Email(message = "Email should be valid.")
  private String email;

  @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters long.")
  private String password;

}
