package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.response.GetCategoryResponse;
import com.alkemy.ong.infrastructure.rest.response.ListCategoryResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ListCategoryMapper {

  private final GetCategoryMapper getCategoryMapper;

  public ListCategoryResponse toResponse(List<Category> categoryList) {
    if (categoryList == null || categoryList.isEmpty()) {
      return new ListCategoryResponse(Collections.emptyList());
    }
    List<GetCategoryResponse> categories = new ArrayList<>(categoryList.size());
    for (Category category : categoryList) {
      categories.add(getCategoryMapper.toResponse(category));
    }
    return new ListCategoryResponse(categories);
  }
}
