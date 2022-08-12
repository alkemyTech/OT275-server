package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IListFooUseCase;
import com.alkemy.ong.infrastructure.rest.mapper.FooMapper;
import com.alkemy.ong.infrastructure.rest.response.ListFooResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooResource {

  @Autowired
  private IListFooUseCase listFooUseCase;

  @Autowired
  private FooMapper fooMapper;


  @GetMapping(value = "/foos", produces = {"application/json"})
  public ResponseEntity<ListFooResponse> list() {
    return ResponseEntity.ok().body(fooMapper.toResponse(listFooUseCase.findAll()));
  }

}
