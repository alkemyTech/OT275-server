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
    categoryResponse.setName(categoryResponse.getName());
    categoryResponse.setContent(categoryResponse.getContent());
    categoryResponse.setImageUrl(categoryResponse.getImageUrl());

    return categoryResponse;
  }

}
