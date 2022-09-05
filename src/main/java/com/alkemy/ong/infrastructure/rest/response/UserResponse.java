package com.alkemy.ong.infrastructure.rest.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {

  private String firstName;
  private String lastName;
  private String email;
  private String role;

}
