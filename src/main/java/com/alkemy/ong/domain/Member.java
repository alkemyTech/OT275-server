package com.alkemy.ong.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

  private Long memberId;
  private String name;
  private SocialMedia socialMedia;
  private String imageUrl;
  private String description;
}
