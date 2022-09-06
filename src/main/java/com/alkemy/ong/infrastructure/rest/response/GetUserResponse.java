package com.alkemy.ong.infrastructure.rest.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetUserResponse {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String role;

}
