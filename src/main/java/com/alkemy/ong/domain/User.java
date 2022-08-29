package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String imageUrl;
  private Role role;
  private Boolean softDeleted;

}

