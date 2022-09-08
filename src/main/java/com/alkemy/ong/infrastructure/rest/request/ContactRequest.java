package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.CharactersWithWhiteSpaces;
import javax.validation.constraints.Email;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class ContactRequest {

  @CharactersWithWhiteSpaces(message = "Name must contain only spaces and letters.")
  private String name;
  private String phone;
  @Email(message = "Email should be valid.")
  private String email;
  @Length(max = 150, message = "Message length must be shorter than 150 characters")
  private String message;
}
