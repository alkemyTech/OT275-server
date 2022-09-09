package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Contact;
import java.util.List;

public interface IContactRepository {

  List<Contact> findAll();

  Contact create(Contact contact);

}
