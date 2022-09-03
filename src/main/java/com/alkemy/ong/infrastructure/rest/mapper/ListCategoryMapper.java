package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.response.CategoryShortResponse;
import com.alkemy.ong.infrastructure.rest.response.ListCategoryResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ListCategoryMapper {

  private final ShortCategoryMapper shortCategoryMapper;

  public ListCategoryResponse toResponse(List<Category> categoryList) {
    if (categoryList == null || categoryList.isEmpty()) {
      return new ListCategoryResponse(Collections.emptyList());
    }
    List<CategoryShortResponse> categories = new ArrayList<>();
    for (Category category : categoryList) {
      categories.add(shortCategoryMapper.toResponse(category));
    }
    return new ListCategoryResponse(categories);
  }
}
