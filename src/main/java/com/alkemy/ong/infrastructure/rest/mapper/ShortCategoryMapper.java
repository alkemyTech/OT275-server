package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.response.CategoryShortResponse;
import org.springframework.stereotype.Component;

@Component
public class ShortCategoryMapper {

  public CategoryShortResponse toResponse(Category category) {
    CategoryShortResponse categoryShortResponse = new CategoryShortResponse();
    categoryShortResponse.setDescription(category.getDescription());
    categoryShortResponse.setImageUrl(category.getImageUrl());
    categoryShortResponse.setName(category.getName());
    return categoryShortResponse;
  }

}
