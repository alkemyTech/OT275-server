package com.alkemy.ong.infrastructure.rest.response.member;

import com.alkemy.ong.infrastructure.rest.response.common.SocialMediaResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetMemberResponse {

  private Long memberId;
  private String name;
  private SocialMediaResponse socialMedia;
  private String imageUrl;
  private String description;

}
