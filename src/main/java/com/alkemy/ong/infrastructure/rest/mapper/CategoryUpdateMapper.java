package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.rest.request.CategoryUpdateRequest;
import com.alkemy.ong.infrastructure.rest.response.CategoryUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryUpdateMapper {

  public Category toUpdateDomain(
      Identifiable<Long> identifiable,
      CategoryUpdateRequest categoryUpdateRequest) {
    if (categoryUpdateRequest == null) {
      return null;
    }
    Category category = new Category();
    category.setCategoryId(identifiable.getId());
    category.setName(categoryUpdateRequest.getName());
    category.setDescription(categoryUpdateRequest.getDescription());
    category.setImageUrl(categoryUpdateRequest.getImageUrl());
    return category;
  }

  public CategoryUpdateResponse toResponse(Category updatedCategory) {
    if (updatedCategory == null) {
      return null;
    }
    CategoryUpdateResponse categoryUpdateResponse = new CategoryUpdateResponse();
    categoryUpdateResponse.setCategoryId(updatedCategory.getCategoryId());
    categoryUpdateResponse.setName(updatedCategory.getName());
    categoryUpdateResponse.setDescription(updatedCategory.getDescription());
    categoryUpdateResponse.setImageUrl(updatedCategory.getImageUrl());
    return categoryUpdateResponse;
  }
}
