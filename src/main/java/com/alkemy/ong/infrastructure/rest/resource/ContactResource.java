package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateContactUseCase;
import com.alkemy.ong.application.service.usecase.IListContactUseCase;
import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.rest.mapper.ContactMapper;
import com.alkemy.ong.infrastructure.rest.mapper.ListContactMapper;
import com.alkemy.ong.infrastructure.rest.request.ContactRequest;
import com.alkemy.ong.infrastructure.rest.response.ContactResponse;
import com.alkemy.ong.infrastructure.rest.response.ListContactResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contacts")
public class ContactResource {

  private final IListContactUseCase listContactUseCase;
  private final ICreateContactUseCase createContactUseCase;
  private final ContactMapper contactMapper;
  private final ListContactMapper listContactMapper;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ContactResponse> create(@Valid @RequestBody ContactRequest contactRequest) {
    Contact savedContact = createContactUseCase.create(contactMapper.toDomain(contactRequest));
    return new ResponseEntity<>(contactMapper.toResponse(savedContact), HttpStatus.CREATED);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListContactResponse> list() {
    return ResponseEntity.ok().body(listContactMapper.toResponse(listContactUseCase.findAll()));
  }

}
