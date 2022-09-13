package com.alkemy.ong.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNewsResponse {

  private Long id;
  private String name;
  private String content;
  private String imageUrl;
  private GetCategoryResponse category;

}
