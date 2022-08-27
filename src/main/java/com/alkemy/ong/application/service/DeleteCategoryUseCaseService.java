package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.domain.Identifiable;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCategoryUseCaseService implements IDeleteCategoryUseCase {

  private final ICategoryRepository categoryRepository;

  @Transactional
  @Override
  public void delete(Identifiable<Long> identifiable) {
    if (!categoryRepository.exist(identifiable)) {
      throw new ObjectNotFound(ErrorMessage.OBJECT_NOT_FOUND.getMessage("Category"));
    }
    categoryRepository.delete(identifiable);
  }
}
