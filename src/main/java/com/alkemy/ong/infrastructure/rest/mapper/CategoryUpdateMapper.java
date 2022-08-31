package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.request.CategoryUpdateRequest;
import com.alkemy.ong.infrastructure.rest.response.CategoryUpdateResponse;

public class CategoryUpdateMapper {
  public Category toDomain(CategoryUpdateRequest categoryUpdateRequest){
    if (categoryUpdateRequest == null){
      return null;
    }
    Category category = new Category();
    category.setName(categoryUpdateRequest.getName());
    category.setDescription(categoryUpdateRequest.getDescription());
    category.setImageUrl(categoryUpdateRequest.getImageUrl());
    return category;
  }

  public CategoryUpdateResponse toResponse(Category updatedCategory) {
    if (updatedCategory == null){
      return null;
    }
    CategoryUpdateResponse categoryUpdateResponse = new CategoryUpdateResponse();
    categoryUpdateResponse.setName(updatedCategory.getName());
    categoryUpdateResponse.setDescription(updatedCategory.getDescription());
    categoryUpdateResponse.setImageUrl(updatedCategory.getImageUrl());
    return categoryUpdateResponse;
  }
}
