package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationRequest {

  @Email(message = "Email has invalid format.")
  private String email;

  @Size(message = "Password must be between 8 and 16 characters long.", min = 8, max = 16)
  private String password;

}


