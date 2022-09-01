package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;

public class CategoryEntityMapper {

  public Category toDomain(CategoryEntity entity) {
    if (entity == null) {
      return null;
    }

    Category category = new Category();
    category.setName(entity.getName());
    category.setContent(category.getContent());
    category.setImageUrl(category.getImageUrl());

    return category;
  }
}
