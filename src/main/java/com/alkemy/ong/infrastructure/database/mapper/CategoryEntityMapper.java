package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    categoryEntity.setCategoryId(category.getId());
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
    category.setId(categoryEntity.getCategoryId());
    category.setName(categoryEntity.getName());
    category.setDescription(categoryEntity.getDescription());
    category.setImageUrl(categoryEntity.getImageUrl());
    return category;
  }

  public List<Category> toDomain(List<CategoryEntity> categoryEntities) {
    if (categoryEntities == null || categoryEntities.isEmpty()) {
      return Collections.emptyList();
    }
    List<Category> categories = new ArrayList<>(categoryEntities.size());
    for (CategoryEntity categoryEntity : categoryEntities) {
      categories.add(toDomain(categoryEntity));
    }
    return categories;
  }
}
