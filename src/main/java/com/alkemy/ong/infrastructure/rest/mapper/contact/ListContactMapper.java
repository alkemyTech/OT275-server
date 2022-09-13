package com.alkemy.ong.infrastructure.rest.mapper.contact;

import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.rest.response.contact.ListContactResponse;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ListContactMapper {

  private final GetContactMapper getContactMapper;

  public ListContactResponse toResponse(List<Contact> contacts) {
    if (contacts == null || contacts.isEmpty()) {
      return new ListContactResponse(Collections.emptyList());
    }
    return new ListContactResponse(getContactMapper.toResponse(contacts));
  }

}
