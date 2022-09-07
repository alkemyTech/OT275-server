package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateNewsUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteNewsUseCase;
import com.alkemy.ong.application.service.usecase.IGetNewsUseCase;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.mapper.CreateNewsMapper;
import com.alkemy.ong.infrastructure.rest.mapper.GetNewsMapper;
import com.alkemy.ong.infrastructure.rest.request.CreateNewsRequest;
import com.alkemy.ong.infrastructure.rest.response.GetNewsResponse;
import com.alkemy.ong.infrastructure.rest.response.NewsResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/news")
public class NewsResource {

  private final IDeleteNewsUseCase deleteNewsUseCase;
  private final IGetNewsUseCase getNewsUseCase;
  private final GetNewsMapper getNewsMapper;
  private final ICreateNewsUseCase createNewsUseCase;
  private final CreateNewsMapper createNewsMapper;

  @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteNewsUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetNewsResponse> get(@PathVariable Long id) {
    return ResponseEntity.ok(getNewsMapper.toResponse(getNewsUseCase.get(() -> id)));
  }

  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<NewsResponse> add(@Valid @RequestBody CreateNewsRequest newsRequest) {
    News news = createNewsMapper.toDomain(newsRequest);
    News createdNews = createNewsUseCase.add(news);
    return new ResponseEntity<>(createNewsMapper.toResponse(createdNews), HttpStatus.CREATED);
  }

}
