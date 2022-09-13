package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.contact.usecase.ICreateContactUseCase;
import com.alkemy.ong.application.service.contact.usecase.IListContactUseCase;
import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.rest.mapper.contact.CreateContactMapper;
import com.alkemy.ong.infrastructure.rest.mapper.contact.ListContactMapper;
import com.alkemy.ong.infrastructure.rest.request.CreateContactRequest;
import com.alkemy.ong.infrastructure.rest.response.CreateContactResponse;
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
  private final CreateContactMapper createContactMapper;
  private final ListContactMapper listContactMapper;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CreateContactResponse> create(
      @Valid @RequestBody CreateContactRequest createContactRequest) {
    Contact contact = createContactUseCase.create(
        createContactMapper.toDomain(createContactRequest));
    return new ResponseEntity<>(createContactMapper.toResponse(contact), HttpStatus.CREATED);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListContactResponse> list() {
    return ResponseEntity.ok().body(listContactMapper.toResponse(listContactUseCase.findAll()));
  }

}
