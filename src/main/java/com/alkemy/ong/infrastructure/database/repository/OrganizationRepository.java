package com.alkemy.ong.infrastructure.database.repository;

import org.springframework.stereotype.Component;
import com.alkemy.ong.application.repository.IOrganizationRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OrganizationRepository implements IOrganizationRepository {

  private final IOrganizationSpringRepository organizationRepository;
  
}
