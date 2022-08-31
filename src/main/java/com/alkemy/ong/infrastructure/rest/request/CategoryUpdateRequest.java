package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.request.validation.Name;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CategoryUpdateRequest {

  @NotEmpty
  @Pattern(
      regexp = "^\\p{L}+[\\p{L}\\s]*$",
      message = "Category name must contain only spaces and letters.")
  private String name;
  private String description;
  private String imageUrl;

}
