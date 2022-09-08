package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.Testimonial;

public interface ITestimonialRepository {

  void delete(Identifiable<Long> identifiable);

  boolean exists(Identifiable<Long> identifiable);

  Testimonial save(Testimonial testimonial);
}
