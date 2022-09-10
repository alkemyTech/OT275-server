package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IContactRepository;
import com.alkemy.ong.application.service.usecase.ICreateContactUseCase;
import com.alkemy.ong.domain.Contact;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateContactUseCaseService implements ICreateContactUseCase {

  private final IContactRepository contactRepository;

  @Override
  public Contact create(Contact contact) {
    return contactRepository.create(contact);
  }
}
