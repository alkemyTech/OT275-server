package com.alkemy.ong.domain;

import com.alkemy.ong.application.util.mail.IAddressContact;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact implements IAddressContact {

  private Long id;
  private String name;
  private String phone;
  private String email;
  private String message;

}
