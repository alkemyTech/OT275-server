package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.domain.Identifiable;

public interface IGetCategoryUseCase {

  Category get(Identifiable<Long> identifiable);

}
