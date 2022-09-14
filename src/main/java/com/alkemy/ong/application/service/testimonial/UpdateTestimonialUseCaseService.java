package com.alkemy.ong.application.service.testimonial;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.service.testimonial.usecase.IUpdateTestimonialUseCase;
import com.alkemy.ong.domain.Testimonial;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateTestimonialUseCaseService implements IUpdateTestimonialUseCase {

  private final ITestimonialRepository testimonialRepository;

  @Override
  public Testimonial update(Testimonial testimonial) {
    if (!testimonialRepository.exists(testimonial::getId)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Testimonial"));
    }
    return testimonialRepository.update(testimonial);
  }

}
