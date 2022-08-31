package com.alkemy.ong.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateResponse {
  private Long categoryId;
  private String name;
  private String description;
  private String imageUrl;
}
