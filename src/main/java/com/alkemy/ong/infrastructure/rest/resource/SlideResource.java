package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.slide.usecase.ICreateSlideUseCase;
import com.alkemy.ong.application.service.slide.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.application.service.slide.usecase.IGetSlideUseCase;
import com.alkemy.ong.application.service.slide.usecase.IListSlideUseCase;
import com.alkemy.ong.application.service.slide.usecase.IUpdateSlideUseCase;
import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.mapper.slide.CreateSlideMapper;
import com.alkemy.ong.infrastructure.rest.mapper.slide.GetSlideMapper;
import com.alkemy.ong.infrastructure.rest.mapper.slide.ListSlideMapper;
import com.alkemy.ong.infrastructure.rest.mapper.slide.UpdateSlideMapper;
import com.alkemy.ong.infrastructure.rest.request.slide.CreateSlideRequest;
import com.alkemy.ong.infrastructure.rest.request.slide.UpdateSlideRequest;
import com.alkemy.ong.infrastructure.rest.response.slide.GetSlideResponse;
import com.alkemy.ong.infrastructure.rest.response.slide.ListSlideResponse;
import com.alkemy.ong.infrastructure.rest.response.slide.UpdateSlideResponse;
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

@RequestMapping("/slides")
@RequiredArgsConstructor
@RestController
public class SlideResource {

  private final IDeleteSlideUseCase deleteSlideUseCase;
  private final IListSlideUseCase listSlideUseCase;
  private final IGetSlideUseCase getSlideUseCase;
  private final ICreateSlideUseCase createSlideUseCase;
  private final IUpdateSlideUseCase updateSlideUseCase;
  private final ListSlideMapper listSlideMapper;
  private final GetSlideMapper getSlideMapper;
  private final CreateSlideMapper createSlideMapper;
  private final UpdateSlideMapper updateSlideMapper;

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteSlideUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListSlideResponse> list() {
    return ResponseEntity.ok().body(listSlideMapper
        .toResponse(listSlideUseCase.findAll()));
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetSlideResponse> getBy(@PathVariable Long id) {
    Slide slide = getSlideUseCase.getBy(() -> id);
    return ResponseEntity.ok().body(getSlideMapper.toResponse(slide));
  }

  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetSlideResponse> add(
      @Valid @RequestBody CreateSlideRequest createSlideRequest) {
    Slide slide = createSlideMapper.toDomain(createSlideRequest);
    Slide createdSlide = createSlideUseCase.add(slide);
    return new ResponseEntity<>(createSlideMapper.toResponse(createdSlide), HttpStatus.CREATED);
  }

  @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UpdateSlideResponse> update(@PathVariable Long id,
      @Valid @RequestBody UpdateSlideRequest updateSlideRequest) {
    Slide slide = updateSlideMapper.toDomain(() -> id, updateSlideRequest);
    Slide updatedSlide = updateSlideUseCase.update(slide);
    return ResponseEntity.ok(updateSlideMapper.toResponse(updatedSlide));
  }

}