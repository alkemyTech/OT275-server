package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.response.ListCategoryResponse;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ListCategoryMapper {

  private final GetCategoryMapper getCategoryMapper;

  public ListCategoryResponse toResponse(List<Category> categories) {
    if (categories == null || categories.isEmpty()) {
      return new ListCategoryResponse(Collections.emptyList());
    }
    return new ListCategoryResponse(getCategoryMapper.toResponse(categories));
  }

}
