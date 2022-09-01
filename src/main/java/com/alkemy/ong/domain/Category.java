package com.alkemy.ong.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category {

  private Long id;
  private String name;
  private String description;
  private String imageUrl;

}
