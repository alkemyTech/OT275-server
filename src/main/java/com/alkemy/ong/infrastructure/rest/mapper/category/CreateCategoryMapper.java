package com.alkemy.ong.infrastructure.rest.mapper.category;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.request.category.CreateCategoryRequest;
import com.alkemy.ong.infrastructure.rest.response.category.CreateCategoryResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCategoryMapper {

  public Category toDomain(@Valid CreateCategoryRequest createCategoryRequest) {
    if (createCategoryRequest == null) {
      return null;
    }
    Category category = new Category();
    category.setName(createCategoryRequest.getName());
    category.setDescription(createCategoryRequest.getDescription());
    category.setImageUrl(createCategoryRequest.getImage());
    return category;
  }

  public CreateCategoryResponse toResponse(Category category) {
    if (category == null) {
      return null;
    }
    CreateCategoryResponse createCategoryResponse = new CreateCategoryResponse();
    createCategoryResponse.setId(category.getId());
    createCategoryResponse.setName(category.getName());
    createCategoryResponse.setDescription(category.getDescription());
    createCategoryResponse.setImage(category.getImageUrl());
    return createCategoryResponse;
  }
}
