package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryEntityMapper {

  public Category toDomain(CategoryEntity entity) {
    if (entity == null) {
      return null;
    }

    Category category = new Category();
    category.setName(entity.getName());
    category.setDescription(entity.getDescription());
    category.setImageUrl(entity.getImageUrl());

    return category;
  }
}
