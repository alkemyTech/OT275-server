package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.testimonial.usecase.ICreateTestimonialUseCase;
import com.alkemy.ong.application.service.testimonial.usecase.IDeleteTestimonialUseCase;
import com.alkemy.ong.application.service.testimonial.usecase.IListTestimonialUseCase;
import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.common.PaginatedResultsRetrieved;
import com.alkemy.ong.infrastructure.rest.mapper.testimonial.CreateTestimonialMapper;
import com.alkemy.ong.infrastructure.rest.mapper.testimonial.ListTestimonialMapper;
import com.alkemy.ong.infrastructure.rest.request.testimonial.CreateTestimonialRequest;
import com.alkemy.ong.infrastructure.rest.response.testimonial.GetTestimonialResponse;
import com.alkemy.ong.infrastructure.rest.response.testimonial.ListTestimonialResponse;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/testimonials")
public class TestimonialResource {

  private final IDeleteTestimonialUseCase deleteTestimonialUseCase;

  private final ICreateTestimonialUseCase createTestimonialUseCase;

  private final CreateTestimonialMapper createTestimonialMapper;

  private final IListTestimonialUseCase listTestimonialUseCase;

  private final ListTestimonialMapper listTestimonialMapper;

  private final PaginatedResultsRetrieved resultsRetrieved;

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteTestimonialUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetTestimonialResponse> create(
      @Valid @RequestBody CreateTestimonialRequest testimonialRequest) {
    Testimonial testimonial = createTestimonialUseCase.create(
        createTestimonialMapper.toDomain(testimonialRequest));
    return new ResponseEntity<>(createTestimonialMapper.toResponse(testimonial),
        HttpStatus.CREATED);
  }

  @GetMapping
      (produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListTestimonialResponse> list(@PageableDefault(size = 10) Pageable pageable,
      UriComponentsBuilder uriBuilder, HttpServletResponse response) {
    Page<Testimonial> resultPage = listTestimonialUseCase.findAll(pageable);
    resultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(
        uriBuilder,
        response,
        "/testimonials",
        resultPage.getNumber(),
        resultPage.getTotalPages(),
        resultPage.getSize());
    return ResponseEntity.ok().body(listTestimonialMapper.toResponse(resultPage));
  }
}
