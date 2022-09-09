package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.rest.request.ContactRequest;
import com.alkemy.ong.infrastructure.rest.response.ContactResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateContactMapper {

  public Contact toDomain(ContactRequest request) {
    if (request == null) {
      return null;
    }
    Contact contact = new Contact();
    contact.setName(request.getName());
    contact.setPhone(request.getPhone());
    contact.setEmail(request.getEmail());
    contact.setMessage(request.getMessage());
    return contact;
  }

  public ContactResponse toResponse(Contact contact) {
    if (contact == null) {
      return null;
    }
    ContactResponse response = new ContactResponse();
    response.setId(contact.getId());
    response.setName(contact.getName());
    response.setPhone(contact.getPhone());
    response.setEmail(contact.getEmail());
    response.setMessage(contact.getMessage());
    return response;
  }
}
