package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContactRepository implements IContactRepository {

  private final IContactSpringRepository iContactSpringRepository;


}
