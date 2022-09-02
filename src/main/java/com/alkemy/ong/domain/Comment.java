package com.alkemy.ong.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {

  private Long id;
  private User createdBy;

}
