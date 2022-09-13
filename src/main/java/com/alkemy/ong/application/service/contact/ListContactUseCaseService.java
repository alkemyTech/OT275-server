package com.alkemy.ong.application.service.contact;

import com.alkemy.ong.application.repository.IContactRepository;
import com.alkemy.ong.application.service.contact.usecase.IListContactUseCase;
import com.alkemy.ong.domain.Contact;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListContactUseCaseService implements IListContactUseCase {

  private final IContactRepository contactRepository;

  @Override
  public List<Contact> findAll() {
    return contactRepository.findAll();
  }

}
