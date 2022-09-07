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
    GetContactResponse getContactResponse = new GetContactResponse();
    getContactResponse.setId(contact.getId());
    getContactResponse.setName(contact.getName());
    getContactResponse.setPhone(contact.getPhone());
    getContactResponse.setEmail(contact.getEmail());
    return getContactResponse;
  }

  public List<GetContactResponse> toResponse(List<Contact> contacts) {
    if (contacts == null || contacts.isEmpty()) {
      return Collections.emptyList();
    }
    List<GetContactResponse> getContactResponses = new ArrayList<>(contacts.size());
    for (Contact contact : contacts) {
      getContactResponses.add(toResponse(contact));
    }
    return getContactResponses;
  }

}
