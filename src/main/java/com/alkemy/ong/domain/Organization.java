package com.alkemy.ong.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Organization {

  private String name;
  private String image;
  private String phone;
  private String address;
  private String email;
  private String facebookUrl;
  private String linkedInUrl;
  private String instagramUrl;
  private String aboutUsText;
  private String welcomeText;


}
