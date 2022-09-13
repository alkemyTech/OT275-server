package com.alkemy.ong.infrastructure.rest.response.organization;

import com.alkemy.ong.infrastructure.rest.response.common.SocialMediaResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateOrganizationResponse {

  private String name;
  private String imageUrl;
  private String address;
  private String phone;
  private String email;
  private SocialMediaResponse socialMedia;
  private String aboutUsText;
  private String welcomeText;

}
