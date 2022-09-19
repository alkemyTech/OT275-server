package com.alkemy.ong.builder;

import com.alkemy.ong.domain.Testimonial;

public class TestimonialBuilder {

  public static Testimonial random() {
    return new Testimonial(null, "test", "content", "imageUrl");
  }

}
