package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.comment.usecase.ICreateCommentUseCase;
import com.alkemy.ong.application.service.comment.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.application.service.comment.usecase.IListCommentUseCase;
import com.alkemy.ong.application.service.comment.usecase.IUpdateCommentUseCase;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.mapper.comment.CreateCommentMapper;
import com.alkemy.ong.infrastructure.rest.mapper.comment.ListCommentMapper;
import com.alkemy.ong.infrastructure.rest.mapper.comment.UpdateCommentMapper;
import com.alkemy.ong.infrastructure.rest.request.comment.CreateCommentRequest;
import com.alkemy.ong.infrastructure.rest.request.comment.UpdateCommentRequest;
import com.alkemy.ong.infrastructure.rest.response.comment.FullCommentResponse;
import com.alkemy.ong.infrastructure.rest.response.common.ListCommentResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentResource {

  private final IDeleteCommentUseCase deleteCommentUseCase;
  private final IListCommentUseCase listCommentUseCase;
  private final ICreateCommentUseCase createCommentUseCase;
  private final IUpdateCommentUseCase updateCommentUseCase;
  private final UpdateCommentMapper updateCommentMapper;
  private final ListCommentMapper listCommentMapper;
  private final CreateCommentMapper createCommentMapper;


  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteCommentUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListCommentResponse> list() {
    return ResponseEntity.ok().body(listCommentMapper
        .toResponse(listCommentUseCase.findAllOrderedByTimestamp()));
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<FullCommentResponse> create(
      @Valid @RequestBody CreateCommentRequest createCommentRequest) {
    Comment comment = createCommentUseCase.create(
        createCommentMapper.toDomain(createCommentRequest));
    return new ResponseEntity<>(createCommentMapper.toResponse(comment), HttpStatus.CREATED);
  }

  @PatchMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<FullCommentResponse> update(
      @PathVariable Long id,
      @RequestBody UpdateCommentRequest request) {
    Comment comment = updateCommentUseCase.update(updateCommentMapper.toDomain(id, request));
    return ResponseEntity.ok().body(updateCommentMapper.toResponse(comment));
  }
}
