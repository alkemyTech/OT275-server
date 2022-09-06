package com.alkemy.ong.infrastructure.rest.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetOrganizationResponse {

  private String name;
  private String image;
  private String phone;
  private String address;
  private List<SlideWithTextResponse> slides;
  private SocialMediaResponse socialMedia;

}
