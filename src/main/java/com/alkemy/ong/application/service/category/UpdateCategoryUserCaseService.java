package com.alkemy.ong.application.service.category;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.category.usecase.IUpdateCategoryUseCase;
import com.alkemy.ong.domain.Category;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCategoryUserCaseService implements IUpdateCategoryUseCase {

  private final ICategoryRepository categoryRepository;

  @Override
  public Category update(Category category) {
    if (!categoryRepository.exists(category::getId)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Category"));
    }
    return categoryRepository.update(category);
  }
}
