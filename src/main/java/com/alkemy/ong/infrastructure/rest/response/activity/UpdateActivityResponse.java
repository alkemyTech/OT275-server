package com.alkemy.ong.infrastructure.rest.response.activity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateActivityResponse {

  private Long id;
  private String content;
  private String imageUrl;
  private String name;

}
