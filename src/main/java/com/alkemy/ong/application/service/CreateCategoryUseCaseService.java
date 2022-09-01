package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.CategoryAlreadyExists;
import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.domain.Category;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCategoryUseCaseService implements ICreateCategoryUseCase {

  ICategoryRepository categoryRepository;

  @Override
  public Category post(Category category) {
    if(categoryRepository.exists(category::getId)){
      throw new CategoryAlreadyExists(ErrorMessage.CATEGORY_ALREADY_EXISTS.getMessage("category"));
    }
    return categoryRepository.post(category);
  }
}

