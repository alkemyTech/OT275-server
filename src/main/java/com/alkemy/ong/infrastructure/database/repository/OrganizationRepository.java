package com.alkemy.ong.infrastructure.database.repository;

import org.springframework.stereotype.Component;
import com.alkemy.ong.application.repository.IOrganizationRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrganizationRepository implements IOrganizationRepository {

  private final IOrganizationSpringRepository organizationRepository;
  
}
