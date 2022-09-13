package com.alkemy.ong.infrastructure.rest.response.category;

import com.alkemy.ong.infrastructure.rest.response.category.GetCategoryResponse;
import com.alkemy.ong.infrastructure.rest.response.common.PaginationResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListCategoryResponse extends PaginationResponse {

  private List<GetCategoryResponse> categories;
}