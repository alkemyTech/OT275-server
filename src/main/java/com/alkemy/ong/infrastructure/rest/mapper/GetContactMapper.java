package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.rest.response.GetContactResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetContactMapper {

  public GetContactResponse toResponse(Contact contact) {
    if (contact == null) {
      return null;
    }
    GetContactResponse contactResponse = new GetContactResponse();
    contactResponse.setName(contact.getName());
    contactResponse.setPhone(contact.getPhone());
    contactResponse.setEmail(contact.getEmail());
    return contactResponse;
  }

  public List<GetContactResponse> toResponse(List<Contact> contacts) {
    if (contacts == null || contacts.isEmpty()) {
      return Collections.emptyList();
    }
    List<GetContactResponse> contactResponses = new ArrayList<>(contacts.size());
    for (Contact contact : contacts) {
      contactResponses.add(toResponse(contact));
    }
    return contactResponses;
  }

}
