package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.service.usecase.IGetUserUseCase;
import com.alkemy.ong.application.service.usecase.ILoginUserUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.mapper.AuthenticationMapper;
import com.alkemy.ong.infrastructure.rest.mapper.GetUserDetailsMapper;
import com.alkemy.ong.infrastructure.rest.mapper.RegisterUserMapper;
import com.alkemy.ong.infrastructure.rest.request.AuthenticationRequest;
import com.alkemy.ong.infrastructure.rest.request.RegisterUserRequest;
import com.alkemy.ong.infrastructure.rest.response.AuthenticationResponse;
import com.alkemy.ong.infrastructure.rest.response.GetUserDetailsResponse;
import com.alkemy.ong.infrastructure.rest.response.RegisterUserResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthResource {

  private final ICreateUserUseCase createUserUseCase;
  private final ILoginUserUseCase loginUserUseCase;
  private final RegisterUserMapper registerUserMapper;
  private final AuthenticationMapper authenticationMapper;
  private final IGetUserUseCase getUserMeUseCase;
  private final GetUserDetailsMapper getUserDetailsMapper;

  @PostMapping(
      value = "/register",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RegisterUserResponse> register(
      @Valid @RequestBody RegisterUserRequest registerRequest) {
    User user = createUserUseCase.add(registerUserMapper.toDomain(registerRequest));
    RegisterUserResponse response = registerUserMapper.toResponse(user);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PostMapping(
      value = "/login",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthenticationResponse> login(
      @Valid @RequestBody AuthenticationRequest authenticationRequest) {
    User user = loginUserUseCase.login(authenticationMapper.toDomain(authenticationRequest));
    return ResponseEntity.ok().body(authenticationMapper.toResponse(user));
  }

  @GetMapping(
      value = "/me",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetUserDetailsResponse> getDetails(Authentication authentication) {
    User user = getUserMeUseCase.get(authentication.getName());
    return ResponseEntity.ok(getUserDetailsMapper.toResponse(user));
  }

}
