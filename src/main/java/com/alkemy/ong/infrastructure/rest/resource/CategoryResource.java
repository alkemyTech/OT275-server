package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateCategoryUseCase;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.mapper.CategoryUpdateMapper;
import com.alkemy.ong.infrastructure.rest.request.CategoryUpdateRequest;
import com.alkemy.ong.infrastructure.rest.response.CategoryUpdateResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor
public class CategoryResource {

  private final IUpdateCategoryUseCase updateCategoryUseCase;
  private final IDeleteCategoryUseCase deleteCategoryUseCase;
  private final CategoryUpdateMapper categoryUpdateMapper;

  @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryUpdateResponse> update(
      @PathVariable Long id,
      @Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
    Category category = categoryUpdateMapper.toDomain(() -> id, categoryUpdateRequest);
    Category updatedCategory = updateCategoryUseCase.update(category);
    return new ResponseEntity<>(categoryUpdateMapper.toResponse(updatedCategory), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteCategoryUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
