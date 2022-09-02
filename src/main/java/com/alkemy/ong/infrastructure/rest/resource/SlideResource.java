package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.application.service.usecase.IGetSlideUseCase;
import com.alkemy.ong.application.service.usecase.IListSlideUseCase;
import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.mapper.GetSlideMapper;
import com.alkemy.ong.infrastructure.rest.mapper.ListSlideMapper;
import com.alkemy.ong.infrastructure.rest.response.GetSlideResponse;
import com.alkemy.ong.infrastructure.rest.response.ListSlideResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/slides")
@RequiredArgsConstructor
@RestController
public class SlideResource {

  private final IDeleteSlideUseCase deleteSlideUseCase;

  private final IListSlideUseCase listSlideUseCase;

  private final IGetSlideUseCase getSlideUseCase;

  private final ListSlideMapper listSlideMapper;

  private final GetSlideMapper getSlideMapper;

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

}