package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IListContactUseCase;
import com.alkemy.ong.infrastructure.rest.mapper.ListContactMapper;
import com.alkemy.ong.infrastructure.rest.response.ListContactResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contacts")
public class ContactResource {

  private final IListContactUseCase listContactUseCase;
  private final ListContactMapper listContactMapper;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListContactResponse> list() {
    return ResponseEntity.ok().body(listContactMapper.toResponse(listContactUseCase.findAll()));
  }

}
