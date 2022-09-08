package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.database.entity.ContactEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ContactEntityMapper {

  public Contact toDomain(ContactEntity entity) {
    if (entity == null) {
      return null;
    }
    Contact contact = new Contact();
    contact.setId(entity.getContactId());
    contact.setName(entity.getName());
    contact.setPhone(entity.getPhone());
    contact.setEmail(entity.getEmail());
    contact.setMessage(entity.getMessage());
    return contact;
  }

  public List<Contact> toDomain(List<ContactEntity> contactEntities) {
    if (contactEntities == null || contactEntities.isEmpty()) {
      return Collections.emptyList();
    }
    List<Contact> contacts = new ArrayList<>(contactEntities.size());
    for (ContactEntity contactEntity : contactEntities) {
      contacts.add(toDomain(contactEntity));
    }
    return contacts;
  }

  public ContactEntity toEntity(Contact contact) {
    if (contact == null) {
      return null;
    }
    ContactEntity entity = new ContactEntity();
    entity.setName(contact.getName());
    entity.setPhone(contact.getPhone());
    entity.setEmail(contact.getEmail());
    entity.setMessage(contact.getMessage());
    return entity;
  }
}
