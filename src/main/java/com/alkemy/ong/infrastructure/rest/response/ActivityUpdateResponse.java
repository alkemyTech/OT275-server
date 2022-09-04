package com.alkemy.ong.infrastructure.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityUpdateResponse {

  private Long id;

  private String content;

  private String imageUrl;

  private String name;

}
