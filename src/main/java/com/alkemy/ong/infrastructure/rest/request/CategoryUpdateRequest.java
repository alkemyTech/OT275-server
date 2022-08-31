package com.alkemy.ong.infrastructure.rest.request;

import lombok.Getter;

@Getter
public class CategoryUpdateRequest {

  private String name;
  private String description;
  private String imageUrl;

}
