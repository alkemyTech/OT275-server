package com.alkemy.ong.application.service.testimonial.usecase;

import com.alkemy.ong.domain.Testimonial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IListTestimonialUseCase {

  Page<Testimonial> findAll(Pageable pageable);

}
