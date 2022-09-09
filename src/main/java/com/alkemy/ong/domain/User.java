package com.alkemy.ong.domain;

import com.alkemy.ong.application.util.mail.IAddressContact;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User implements IAddressContact {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String imageUrl;
  private Role role;
  private String token;

}

