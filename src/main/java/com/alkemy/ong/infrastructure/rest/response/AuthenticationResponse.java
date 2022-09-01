package com.alkemy.ong.infrastructure.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {

  private String firstName;
  private String lastName;
  private String email;
  private String imageUrl;
  private String token;

}