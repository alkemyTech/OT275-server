package com.alkemy.ong.infrastructure.rest.response.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUserResponse {

  private Long id;
  private String firstName;
  private String lastName;
  private String imageUrl;

}
