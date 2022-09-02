package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.request.CategoryPostRequest;
import com.alkemy.ong.infrastructure.rest.response.CategoryPostResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryPostMapper {

  public Category toDomain(@Valid CategoryPostRequest postRequest) {
    if (postRequest == null) {
      return null;
    }
    Category category = new Category();
    category.setName(postRequest.getName());
    category.setDescription(postRequest.getDescription());
    category.setImageUrl(postRequest.getImage());
    return category;
  }

  public CategoryPostResponse toResponse(Category category) {
    if (category == null) {
      return null;
    }
    CategoryPostResponse postResponse = new CategoryPostResponse();
    postResponse.setName(category.getName());
    postResponse.setDescription(category.getDescription());
    postResponse.setImage(category.getImageUrl());
    return postResponse;
  }
}
