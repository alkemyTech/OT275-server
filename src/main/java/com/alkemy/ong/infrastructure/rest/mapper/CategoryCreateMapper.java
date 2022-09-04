package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.request.CategoryCreateRequest;
import com.alkemy.ong.infrastructure.rest.response.CategoryCreateResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryCreateMapper {

  public Category toDomain(@Valid CategoryCreateRequest createRequest) {
    if (createRequest == null) {
      return null;
    }
    Category category = new Category();
    category.setName(createRequest.getName());
    category.setDescription(createRequest.getDescription());
    category.setImageUrl(createRequest.getImage());
    return category;
  }

  public CategoryCreateResponse toResponse(Category category) {
    if (category == null) {
      return null;
    }
    CategoryCreateResponse createResponse = new CategoryCreateResponse();
    createResponse.setName(category.getName());
    createResponse.setDescription(category.getDescription());
    createResponse.setImage(category.getImageUrl());
    return createResponse;
  }
}
