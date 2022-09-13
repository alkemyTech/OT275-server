package com.alkemy.ong.infrastructure.rest.response.news;

import com.alkemy.ong.infrastructure.rest.response.category.GetCategoryResponse;
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
