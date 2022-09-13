package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.category.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.category.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.application.service.category.usecase.IGetCategoryUseCase;
import com.alkemy.ong.application.service.category.usecase.IListCategoryUseCase;
import com.alkemy.ong.application.service.category.usecase.IUpdateCategoryUseCase;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.common.PaginatedResultsRetrieved;
import com.alkemy.ong.infrastructure.rest.mapper.category.CreateCategoryMapper;
import com.alkemy.ong.infrastructure.rest.mapper.category.GetCategoryMapper;
import com.alkemy.ong.infrastructure.rest.mapper.category.ListCategoryMapper;
import com.alkemy.ong.infrastructure.rest.mapper.category.UpdateCategoryMapper;
import com.alkemy.ong.infrastructure.rest.request.category.CreateCategoryRequest;
import com.alkemy.ong.infrastructure.rest.request.category.UpdateCategoryRequest;
import com.alkemy.ong.infrastructure.rest.response.category.CreateCategoryResponse;
import com.alkemy.ong.infrastructure.rest.response.category.GetCategoryResponse;
import com.alkemy.ong.infrastructure.rest.response.category.ListCategoryResponse;
import com.alkemy.ong.infrastructure.rest.response.category.UpdateCategoryResponse;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor
public class CategoryResource {

  private final IUpdateCategoryUseCase updateCategoryUseCase;
  private final IDeleteCategoryUseCase deleteCategoryUseCase;
  private final IListCategoryUseCase listCategoryUseCase;
  private final IGetCategoryUseCase getCategoryUseCase;
  private final UpdateCategoryMapper updateCategoryMapper;
  private final CreateCategoryMapper createCategoryMapper;
  private final ICreateCategoryUseCase createCategoryUseCase;
  private final GetCategoryMapper getCategoryMapper;
  private final ListCategoryMapper listCategoryMapper;


  private final PaginatedResultsRetrieved resultsRetrieved;

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
  public ResponseEntity<ListCategoryResponse> list(@PageableDefault(size = 10) Pageable pageable,
      UriComponentsBuilder uriBuilder, HttpServletResponse response) {
    Page<Category> resultPage = listCategoryUseCase.findAll(pageable);
    resultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(
        uriBuilder,
        response,
        "/categories",
        resultPage.getNumber(),
        resultPage.getTotalPages(),
        resultPage.getSize()
    );
    ListCategoryResponse listCategoryResponse = listCategoryMapper.toResponse(resultPage);
    return ResponseEntity.ok().body(listCategoryResponse);
  }

}
