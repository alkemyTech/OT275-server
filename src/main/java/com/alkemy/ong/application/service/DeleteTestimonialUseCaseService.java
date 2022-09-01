package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.application.service.usecase.IDeleteTestimonialUseCase;
import com.alkemy.ong.domain.Identifiable;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteTestimonialUseCaseService implements IDeleteTestimonialUseCase {

  private final ITestimonialRepository testimonialRepository;

  @Transactional
  @Override
  public void delete(Identifiable<Long> identifiable) {
    if (!testimonialRepository.exists(identifiable)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Testimonial"));
    }
    testimonialRepository.delete(identifiable);
  }

}
