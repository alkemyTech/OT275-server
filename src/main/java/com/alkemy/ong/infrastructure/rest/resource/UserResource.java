package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.user.usecase.IDeleteUserUseCase;
import com.alkemy.ong.application.service.user.usecase.IListUserUseCase;
import com.alkemy.ong.application.service.user.usecase.IUpdateUserUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.mapper.user.ListUserMapper;
import com.alkemy.ong.infrastructure.rest.mapper.user.UpdateUserMapper;
import com.alkemy.ong.infrastructure.rest.request.user.UpdateUserRequest;
import com.alkemy.ong.infrastructure.rest.response.user.ListUserResponse;
import com.alkemy.ong.infrastructure.rest.response.user.UpdateUserResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
  private final IListUserUseCase listUserUseCase;
  private final ListUserMapper listUserMapper;
  private final IUpdateUserUseCase updateUserUseCase;
  private final UpdateUserMapper updateUserMapper;

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteUserUseCase.delete(() -> id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListUserResponse> list() {
    ListUserResponse listUserResponse = listUserMapper.toResponse(listUserUseCase.findAll());
    return ResponseEntity.status(HttpStatus.OK).body(listUserResponse);
  }

  @PutMapping(value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UpdateUserResponse> update(@PathVariable Long id,
      @Valid @RequestBody UpdateUserRequest updateUserRequest) {
    User user = updateUserMapper.toDomain(() -> id, updateUserRequest);
    return ResponseEntity.ok(updateUserMapper.toResponse(updateUserUseCase.update(user)));
  }

}