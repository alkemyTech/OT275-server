package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.service.usecase.ICreateTestimonialUseCase;
import com.alkemy.ong.domain.Testimonial;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTestimonialUseCaseService implements ICreateTestimonialUseCase {

  private final ITestimonialRepository testimonialRepository;

  @Override
  public Testimonial create(Testimonial testimonial) {
    return testimonialRepository.save(testimonial);
  }
}
