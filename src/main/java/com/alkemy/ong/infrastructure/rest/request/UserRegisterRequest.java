package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRegisterRequest {

  private String firstName;
  private String lastName;
  private String email;
  private String password;

}
