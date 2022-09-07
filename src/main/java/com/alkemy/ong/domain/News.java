package com.alkemy.ong.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class News {

  private Long id;

  private String name;

  private String content;

  private String imageUrl;

  private Category category;

  private List<Comment> comments;

}
