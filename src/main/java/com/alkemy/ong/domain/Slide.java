package com.alkemy.ong.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Slide {

  private Long id;
  private String imageUrl;
  private Integer order;
  private String text;

}
