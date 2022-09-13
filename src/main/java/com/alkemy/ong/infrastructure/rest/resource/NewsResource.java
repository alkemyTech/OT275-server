package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.news.usecase.ICreateNewsUseCase;
import com.alkemy.ong.application.service.news.usecase.IDeleteNewsUseCase;
import com.alkemy.ong.application.service.news.usecase.IGetNewsUseCase;
import com.alkemy.ong.application.service.news.usecase.IGetNewsWithCommentsUseCase;
import com.alkemy.ong.application.service.news.usecase.IUpdateNewsUseCase;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.mapper.news.CreateNewsMapper;
import com.alkemy.ong.infrastructure.rest.mapper.news.GetNewsMapper;
import com.alkemy.ong.infrastructure.rest.mapper.news.GetNewsWithCommentsMapper;
import com.alkemy.ong.infrastructure.rest.mapper.news.UpdateNewsMapper;
import com.alkemy.ong.infrastructure.rest.request.news.CreateNewsRequest;
import com.alkemy.ong.infrastructure.rest.request.news.UpdateNewsRequest;
import com.alkemy.ong.infrastructure.rest.response.news.GetNewsResponse;
import com.alkemy.ong.infrastructure.rest.response.news.GetNewsWithCommentsResponse;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/news")
public class NewsResource {

  private final IDeleteNewsUseCase deleteNewsUseCase;
  private final IGetNewsUseCase getNewsUseCase;
  private final IGetNewsWithCommentsUseCase getNewsWithCommentsUseCase;
  private final GetNewsMapper getNewsMapper;
  private final ICreateNewsUseCase createNewsUseCase;
  private final CreateNewsMapper createNewsMapper;
  private final GetNewsWithCommentsMapper getNewsWithCommentsMapper;
  private final UpdateNewsMapper updateNewsMapper;
  private final IUpdateNewsUseCase updateNewsUseCase;

  @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteNewsUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetNewsResponse> get(@PathVariable Long id) {
    return ResponseEntity.ok(getNewsMapper.toResponse(getNewsUseCase.get(() -> id)));
  }

  @GetMapping(value = "/{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetNewsWithCommentsResponse> getWithComments(@PathVariable Long id) {
    return ResponseEntity.ok(
        getNewsWithCommentsMapper.toResponse(getNewsWithCommentsUseCase.get(() -> id)));
  }

  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetNewsResponse> add(@Valid @RequestBody CreateNewsRequest newsRequest) {
    News news = createNewsMapper.toDomain(newsRequest);
    News createdNews = createNewsUseCase.add(news);
    return new ResponseEntity<>(createNewsMapper.toResponse(createdNews), HttpStatus.CREATED);
  }

  @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetNewsResponse> update(@PathVariable Long id,
      @Valid @RequestBody UpdateNewsRequest updateNewsRequest) {
    News news = updateNewsMapper.toDomain(() -> id, updateNewsRequest);
    return ResponseEntity.ok(updateNewsMapper.toResponse(updateNewsUseCase.update(news)));
  }
}


