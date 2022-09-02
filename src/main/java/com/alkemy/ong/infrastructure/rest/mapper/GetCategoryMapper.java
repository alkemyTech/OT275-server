package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.response.GetCategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class GetCategoryMapper {

  public GetCategoryResponse toResponse(Category category) {
    if (category == null) {
      return null;
    }

    GetCategoryResponse categoryResponse = new GetCategoryResponse();
    categoryResponse.setId(category.getId());
    categoryResponse.setName(category.getName());
    categoryResponse.setDescription(category.getDescription());
    categoryResponse.setImageUrl(category.getImageUrl());

    return categoryResponse;
  }

}
