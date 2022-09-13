package com.alkemy.ong.infrastructure.rest.response.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateActivityResponse {

  private Long id;
  private String name;
  private String content;
  private String imageUrl;

}
