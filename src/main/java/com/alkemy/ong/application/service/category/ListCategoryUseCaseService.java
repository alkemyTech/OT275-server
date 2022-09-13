package com.alkemy.ong.application.service.category;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.category.usecase.IListCategoryUseCase;
import com.alkemy.ong.domain.Category;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class ListCategoryUseCaseService implements IListCategoryUseCase {

  private final ICategoryRepository categoryRepository;

  @Override
  public Page<Category> findAll(Pageable pageable) {
    return categoryRepository.findAll(pageable);
  }
}
