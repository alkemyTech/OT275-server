package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IGetCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateCategoryUseCase;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.mapper.CategoryCreateMapper;
import com.alkemy.ong.infrastructure.rest.mapper.CategoryUpdateMapper;
import com.alkemy.ong.infrastructure.rest.mapper.GetCategoryMapper;
import com.alkemy.ong.infrastructure.rest.request.CategoryCreateRequest;
import com.alkemy.ong.infrastructure.rest.request.CategoryUpdateRequest;
import com.alkemy.ong.infrastructure.rest.response.CategoryCreateResponse;
import com.alkemy.ong.infrastructure.rest.response.CategoryUpdateResponse;
import com.alkemy.ong.infrastructure.rest.response.GetCategoryResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
  private final IGetCategoryUseCase getCategoryUseCase;
  private final CategoryUpdateMapper categoryUpdateMapper;
  private final CategoryCreateMapper categoryCreateMapper;
  private final ICreateCategoryUseCase createCategoryUseCase;
  private final GetCategoryMapper getCategoryMapper;

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

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryCreateResponse> create(
      @Valid @RequestBody CategoryCreateRequest createRequest) {
    Category category = categoryCreateMapper.toDomain(createRequest);
    Category savedCategory = createCategoryUseCase.create(category);
    CategoryCreateResponse response = categoryCreateMapper.toResponse(savedCategory);
    return new ResponseEntity<>(response, HttpStatus.CREATED);

  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetCategoryResponse> get(@PathVariable Long id) {
    Category category = getCategoryUseCase.get(() -> id);
    return new ResponseEntity<>(getCategoryMapper.toResponse(category), HttpStatus.OK);

  }

}
