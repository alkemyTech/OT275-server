package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.Testimonial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITestimonialRepository {

  void delete(Identifiable<Long> identifiable);

  boolean exists(Identifiable<Long> identifiable);

  Testimonial save(Testimonial testimonial);

  Page<Testimonial> findAll(Pageable pageable);

  Testimonial update(Testimonial testimonial);

}
