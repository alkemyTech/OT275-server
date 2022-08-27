package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ITestimonialRepository;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.database.repository.abstraction.ITestimonialSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TestimonialRepository implements ITestimonialRepository {

  private final ITestimonialSpringRepository testimonialSpringRepository;

  @Override
  public void delete(Identifiable<Long> identifiable) {
    testimonialSpringRepository.softDelete(identifiable.getId());
  }

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return testimonialSpringRepository.exists(identifiable.getId()).isPresent();
  }

}
