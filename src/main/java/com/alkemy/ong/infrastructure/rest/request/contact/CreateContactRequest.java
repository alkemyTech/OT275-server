package com.alkemy.ong.infrastructure.rest.request.contact;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateContactRequest {

  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String name;
  private String phone;
  @Email(message = "Email should be valid.")
  private String email;
  @Size(max = 150, message = "Message length must be shorter than 150 characters")
  private String message;
}
