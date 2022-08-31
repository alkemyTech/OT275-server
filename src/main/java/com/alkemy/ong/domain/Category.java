package com.alkemy.ong.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {

  private Long categoryId;
  private String name;
  private String description;
  private String imageUrl;

}
