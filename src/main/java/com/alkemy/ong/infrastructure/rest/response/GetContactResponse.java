package com.alkemy.ong.infrastructure.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetContactResponse {

  private String name;
  private String phone;
  private String email;

}
