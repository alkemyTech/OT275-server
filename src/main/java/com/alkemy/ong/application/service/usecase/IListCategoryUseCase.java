package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IListCategoryUseCase {

  Page<Category> findAll(Pageable pageable);
}
