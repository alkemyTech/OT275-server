package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.database.mapper.OrganizationMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IOrganizationSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrganizationRepository implements IOrganizationRepository {

  private final OrganizationMapper organizationMapper;
  private final IOrganizationSpringRepository organizationSpringRepository;

  public Organization getOrganization() {
    return organizationMapper.toDomain(organizationSpringRepository.findAll().get(0));
  }

}
