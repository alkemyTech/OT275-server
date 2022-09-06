package com.alkemy.ong.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class News {
  private Long Id;
  private String name;
  private String content;
  private String imageUrl;
  private Category category;
  private Long createTimestamp;
}
