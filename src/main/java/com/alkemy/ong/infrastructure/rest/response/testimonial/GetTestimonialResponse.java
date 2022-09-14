package com.alkemy.ong.infrastructure.rest.response.testimonial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetTestimonialResponse {

  private Long id;
  private String name;
  private String content;
  private String imageUrl;

}
