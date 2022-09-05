package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import com.alkemy.ong.application.service.usecase.IListUserUseCase;
import com.alkemy.ong.infrastructure.rest.mapper.UserMapper;
import com.alkemy.ong.infrastructure.rest.response.ListUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserResource {

  private final IDeleteUserUseCase deleteUserUseCase;
  private final IListUserUseCase listUserUseCase;
  private final UserMapper userMapper;

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteUserUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListUserResponse> get() {
    ListUserResponse response = userMapper.toResponse(listUserUseCase.findAll());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

}