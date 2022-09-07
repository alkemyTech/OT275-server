package com.alkemy.ong.infrastructure.rest.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NewsResponse {

  private Long id;
  private String name;
  private String text;
  private String imageUrl;

}
