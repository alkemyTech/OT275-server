package com.alkemy.ong.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String imageUrl;
  private Role role;
  private String token;
  private String softDeleted;

}

