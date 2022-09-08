package com.alkemy.ong.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponse {

  private Long id;
  private String name;
  private String phone;
  private String email;
  private String message;
}
