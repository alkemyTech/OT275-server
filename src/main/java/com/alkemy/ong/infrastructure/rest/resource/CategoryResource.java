package com.alkemy.ong.infrastructure.rest.resource;


import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IGetCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IListCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateCategoryUseCase;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.mapper.CreateCategoryMapper;
import com.alkemy.ong.infrastructure.rest.mapper.GetCategoryMapper;
import com.alkemy.ong.infrastructure.rest.mapper.ListCategoryMapper;
import com.alkemy.ong.infrastructure.rest.response.GetCategoryResponse;
import com.alkemy.ong.infrastructure.rest.response.ListCategoryResponse;
import com.alkemy.ong.infrastructure.rest.mapper.UpdateCategoryMapper;
import com.alkemy.ong.infrastructure.rest.request.CreateCategoryRequest;
import com.alkemy.ong.infrastructure.rest.request.UpdateCategoryRequest;
import com.alkemy.ong.infrastructure.rest.response.CreateCategoryResponse;
import com.alkemy.ong.infrastructure.rest.response.UpdateCategoryResponse;
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
  private final IListCategoryUseCase listcategoryUseCase;
  private final IGetCategoryUseCase getCategoryUseCase;
  private final UpdateCategoryMapper updateCategoryMapper;
  private final CreateCategoryMapper createCategoryMapper;
  private final ICreateCategoryUseCase createCategoryUseCase;
  private final GetCategoryMapper getCategoryMapper;

  private final ListCategoryMapper listCategoryMapper;

  @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UpdateCategoryResponse> update(
      @PathVariable Long id,
      @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
    Category category = updateCategoryMapper.toDomain(() -> id, updateCategoryRequest);
    Category updatedCategory = updateCategoryUseCase.update(category);
    return new ResponseEntity<>(updateCategoryMapper.toResponse(updatedCategory), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteCategoryUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CreateCategoryResponse> create(
      @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
    Category category = createCategoryUseCase.create(
        createCategoryMapper.toDomain(createCategoryRequest));
    return new ResponseEntity<>(createCategoryMapper.toResponse(category), HttpStatus.CREATED);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetCategoryResponse> get(@PathVariable Long id) {
    Category category = getCategoryUseCase.get(() -> id);
    return new ResponseEntity<>(getCategoryMapper.toResponse(category), HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListCategoryResponse> list() {
    ListCategoryResponse listCategoryResponse = listCategoryMapper.toResponse(
        listcategoryUseCase.findAll());
    return new ResponseEntity<>(listCategoryResponse, HttpStatus.OK);
  }

}
