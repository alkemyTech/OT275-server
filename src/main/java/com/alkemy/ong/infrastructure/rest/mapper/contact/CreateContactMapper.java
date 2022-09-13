package com.alkemy.ong.infrastructure.rest.mapper.contact;

import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.rest.request.contact.CreateContactRequest;
import com.alkemy.ong.infrastructure.rest.response.contact.CreateContactResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateContactMapper {

  public Contact toDomain(CreateContactRequest createContactRequest) {
    if (createContactRequest == null) {
      return null;
    }
    Contact contact = new Contact();
    contact.setName(createContactRequest.getName());
    contact.setPhone(createContactRequest.getPhone());
    contact.setEmail(createContactRequest.getEmail());
    contact.setMessage(createContactRequest.getMessage());
    return contact;
  }

  public CreateContactResponse toResponse(Contact contact) {
    if (contact == null) {
      return null;
    }
    CreateContactResponse createContactResponse = new CreateContactResponse();
    createContactResponse.setId(contact.getId());
    createContactResponse.setName(contact.getName());
    createContactResponse.setPhone(contact.getPhone());
    createContactResponse.setEmail(contact.getEmail());
    createContactResponse.setMessage(contact.getMessage());
    return createContactResponse;
  }
}
