package com.alkemy.ong.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Activity {

  private Long id;
  private String content;
  private String imageUrl;
  private String name;

}
