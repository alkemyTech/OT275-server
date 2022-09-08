package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IContactRepository;
import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.database.entity.ContactEntity;
import com.alkemy.ong.infrastructure.database.mapper.ContactEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IContactSpringRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContactRepository implements IContactRepository {

  private final IContactSpringRepository contactSpringRepository;
  private final ContactEntityMapper contactEntityMapper;

  @Override
  public List<Contact> findAll() {
    return contactEntityMapper.toDomain(contactSpringRepository.findAllByDeletedAtNull());
  }

  @Override
  public Optional<Object> findBy(String name) {
    Optional<ContactEntity> contactEntity = contactSpringRepository.findByName(name);
    if (contactEntity.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(contactEntityMapper.toDomain(contactEntity.get()));
  }

  @Override
  public Contact create(Contact contact) {
    ContactEntity entityToSave = contactEntityMapper.toEntity(contact);
    return contactEntityMapper.toDomain(contactSpringRepository.save(entityToSave));
  }
}
