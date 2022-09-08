package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Contact;
import java.util.List;
import java.util.Optional;

public interface IContactRepository {

  List<Contact> findAll();

  Optional<Object> findBy(String name);

  Contact create(Contact contact);

}
