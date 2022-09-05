package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Category;
import java.util.List;

public interface IListCategoryUseCase {

  List<Category> findAll();
}
