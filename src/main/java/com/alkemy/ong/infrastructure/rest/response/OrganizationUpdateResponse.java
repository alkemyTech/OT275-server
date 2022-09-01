package com.alkemy.ong.infrastructure.rest.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrganizationUpdateResponse {

  private String name;
  private String imageUrl;
  private String address;
  private String phone;
  private String email;
  private String facebookUrl;
  private String linkedInUrl;
  private String instagramUrl;
  private String aboutUsText;
  private String welcomeText;

}
