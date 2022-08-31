package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryEntityMapper {

  public CategoryEntity toEntity(Category category) {
    if (category == null) {
      return null;
    }
    CategoryEntity categoryEntity = new CategoryEntity();
    categoryEntity.setName(category.getName());
    categoryEntity.setDescription(category.getDescription());
    categoryEntity.setImageUrl(category.getImageUrl());
    return categoryEntity;
  }

  public Category toDomain(CategoryEntity categoryEntity) {
    if (categoryEntity == null) {
      return null;
    }
    Category category = new Category();
    category.setName(categoryEntity.getName());
    category.setDescription(categoryEntity.getDescription());
    category.setImageUrl(categoryEntity.getImageUrl());
    return category;
  }
}
