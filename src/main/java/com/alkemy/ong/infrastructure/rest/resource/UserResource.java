package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserResource {

  private final IDeleteUserUseCase deleteUserUseCase;

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteUserUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}