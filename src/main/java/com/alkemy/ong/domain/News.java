package com.alkemy.ong.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class News {

  private Long id;
  private String name;
  private String content;
  private String imageUrl;
  private Long createTimestamp;
}
