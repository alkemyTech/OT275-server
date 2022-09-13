package com.alkemy.ong.infrastructure.rest.mapper.category;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.rest.request.UpdateCategoryRequest;
import com.alkemy.ong.infrastructure.rest.response.UpdateCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCategoryMapper {

  public Category toDomain(Identifiable<Long> identifiable,
      UpdateCategoryRequest updateCategoryRequest) {
    if (updateCategoryRequest == null) {
      return null;
    }
    Category category = new Category();
    category.setId(identifiable.getId());
    category.setName(updateCategoryRequest.getName());
    category.setDescription(updateCategoryRequest.getDescription());
    category.setImageUrl(updateCategoryRequest.getImageUrl());
    return category;
  }

  public UpdateCategoryResponse toResponse(Category updatedCategory) {
    if (updatedCategory == null) {
      return null;
    }
    UpdateCategoryResponse updateCategoryResponse = new UpdateCategoryResponse();
    updateCategoryResponse.setId(updatedCategory.getId());
    updateCategoryResponse.setName(updatedCategory.getName());
    updateCategoryResponse.setDescription(updatedCategory.getDescription());
    updateCategoryResponse.setImageUrl(updatedCategory.getImageUrl());
    return updateCategoryResponse;
  }
}
