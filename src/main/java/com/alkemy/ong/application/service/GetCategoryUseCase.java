package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFoundException;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.usecase.IGetCategoryUseCase;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.domain.Identifiable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetCategoryUseCase implements IGetCategoryUseCase {

  private final ICategoryRepository categoryRepository;

  @Override
  public Category get(Identifiable<Long> identifiable) {
    if (!categoryRepository.exists(identifiable)) {
      throw new ObjectNotFoundException(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Category"));
    }
    return categoryRepository.get(identifiable);
  }
}
