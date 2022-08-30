package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.mapper.UserRegisterMapper;
import com.alkemy.ong.infrastructure.rest.request.UserRegisterRequest;
import com.alkemy.ong.infrastructure.rest.response.UserRegisterResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthResource {

  private final ICreateUserUseCase createUserUseCase;
  private final UserRegisterMapper userRegisterMapper;

  @PostMapping(
      value = "/register",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserRegisterResponse> register(
      @Valid @RequestBody UserRegisterRequest registerRequest) {
    User user = userRegisterMapper.toDomain(registerRequest);
    User savedUser = createUserUseCase.add(user);
    UserRegisterResponse response = userRegisterMapper.toResponse(savedUser);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }
}
