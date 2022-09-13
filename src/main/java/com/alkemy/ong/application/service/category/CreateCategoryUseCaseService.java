package com.alkemy.ong.application.service.category;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.category.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.domain.Category;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCategoryUseCaseService implements ICreateCategoryUseCase {

  private final ICategoryRepository categoryRepository;

  @Override
  public Category create(Category category) {
    return categoryRepository.save(category);
  }

}

