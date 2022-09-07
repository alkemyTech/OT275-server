package com.alkemy.ong.infrastructure.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetNewsResponse {

  private Long id;

  private String name;

  private String content;

  private String imageUrl;

  private GetCategoryResponse category;

}
