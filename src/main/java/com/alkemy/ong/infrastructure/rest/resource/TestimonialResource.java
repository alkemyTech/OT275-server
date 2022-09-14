package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.testimonial.usecase.ICreateTestimonialUseCase;
import com.alkemy.ong.application.service.testimonial.usecase.IDeleteTestimonialUseCase;
import com.alkemy.ong.application.service.testimonial.usecase.IUpdateTestimonialUseCase;
import com.alkemy.ong.domain.Testimonial;
import com.alkemy.ong.infrastructure.rest.mapper.testimonial.CreateTestimonialMapper;
import com.alkemy.ong.infrastructure.rest.mapper.testimonial.UpdateTestimonialMapper;
import com.alkemy.ong.infrastructure.rest.request.testimonial.CreateTestimonialRequest;
import com.alkemy.ong.infrastructure.rest.request.testimonial.UpdateTestimonialRequest;
import com.alkemy.ong.infrastructure.rest.response.testimonial.GetTestimonialResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/testimonials")
public class TestimonialResource {

  private final IDeleteTestimonialUseCase deleteTestimonialUseCase;

  private final ICreateTestimonialUseCase createTestimonialUseCase;

  private final CreateTestimonialMapper createTestimonialMapper;

  private final UpdateTestimonialMapper updateTestimonialMapper;

  private final IUpdateTestimonialUseCase updateTestimonialUseCase;

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

  @PutMapping(
      value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetTestimonialResponse> update(@PathVariable Long id,
      @Valid @RequestBody UpdateTestimonialRequest testimonialRequest) {
    Testimonial testimonial = updateTestimonialMapper.toDomain(() -> id, testimonialRequest);
    Testimonial updatedTestimonial = updateTestimonialUseCase.update(testimonial);
    return ResponseEntity.ok(createTestimonialMapper.toResponse(updatedTestimonial));
  }

}
