package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.domain.Category;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCategoryUseCaseService implements ICreateCategoryUseCase {

  ICategoryRepository categoryRepository;

  @Override
  public Category create(Category category) {

    return categoryRepository.create(category);
  }
}

