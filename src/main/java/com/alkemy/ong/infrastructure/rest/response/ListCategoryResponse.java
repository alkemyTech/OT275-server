package com.alkemy.ong.infrastructure.rest.response;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListCategoryResponse {

  private List<CategoryShortResponse> categories;
}