package com.alkemy.ong.application.service.testimonial;

import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.service.testimonial.usecase.IListTestimonialUseCase;
import com.alkemy.ong.domain.Testimonial;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class
ListTestimonialUseCaseService implements IListTestimonialUseCase {

  ITestimonialRepository testimonialRepository;

  @Override
  public Page<Testimonial> findAll(Pageable pageable) {
    return testimonialRepository.findAll(pageable);
  }
}
