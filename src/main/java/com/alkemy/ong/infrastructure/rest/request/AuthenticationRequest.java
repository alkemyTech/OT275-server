package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {

  @Email(message = "Username must be an Email")
  private String email;

  @Size(message = "Password must be between 8 and 16 characters long", min = 8, max = 16)
  private String password;

}
