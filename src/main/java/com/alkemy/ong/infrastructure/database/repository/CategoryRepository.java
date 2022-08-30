package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.database.repository.abstraction.ICategorySpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryRepository implements ICategoryRepository {

  private final ICategorySpringRepository categorySpringRepository;

  @Override
  public void delete(Identifiable<Long> identifiable) {
    categorySpringRepository.softDelete(identifiable.getId());
  }

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return categorySpringRepository.exists(identifiable.getId()).isPresent();
  }
}
