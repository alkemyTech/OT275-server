package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryRepository implements ICategoryRepository {

  private final ICategorySpringRepository categorySpringRepository;

}
