package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.usecase.IListCategoryUseCase;
import com.alkemy.ong.domain.Category;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListCategoryUseCaseService implements IListCategoryUseCase {

  private final ICategoryRepository categoryRepository;

  @Override
  public List<Category> findAll() { return categoryRepository.findAll(); }
}
