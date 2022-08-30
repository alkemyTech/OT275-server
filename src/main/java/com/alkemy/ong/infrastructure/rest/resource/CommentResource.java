package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IDeleteCommentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentResource {

  private final IDeleteCommentUseCase deleteCommentUseCase;

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteCommentUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
