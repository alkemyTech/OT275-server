package com.alkemy.ong.infrastructure.rest.response.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryResponse {

  private Long id;

  private String description;

  private String imageUrl;

  private String name;
}
