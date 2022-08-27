package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.database.repository.abstraction.ISlideSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SlideRepository implements ISlideRepository {

  private final ISlideSpringRepository slideSpringRepository;

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return slideSpringRepository.existsById(identifiable.getId());
  }

  @Override
  public void delete(Identifiable<Long> identifiable) {
    slideSpringRepository.deleteById(identifiable.getId());
  }
}