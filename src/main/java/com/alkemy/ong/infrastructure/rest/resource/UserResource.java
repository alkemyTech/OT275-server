package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateUserUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.mapper.UpdateUserMapper;
import com.alkemy.ong.infrastructure.rest.request.UpdateUserRequest;
import com.alkemy.ong.infrastructure.rest.response.UpdateUserResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RequiredArgsConstructor
@RestController
public class UserResource {

  private final IDeleteUserUseCase deleteUserUseCase;
  private final IUpdateUserUseCase updateUserUseCase;
  private final UpdateUserMapper updateUserMapper;

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteUserUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping(value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UpdateUserResponse> update(@PathVariable Long id,
      @Valid @RequestBody UpdateUserRequest updateUserRequest) {
    User user = updateUserMapper.toDomain(() -> id, updateUserRequest);
    UpdateUserResponse response = updateUserMapper.toResponse(updateUserUseCase.update(user));
    return ResponseEntity.ok(response);
  }
}