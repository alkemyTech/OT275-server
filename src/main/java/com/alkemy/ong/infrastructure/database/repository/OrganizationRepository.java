package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrganizationRepository implements IOrganizationRepository {

  private final IOrganizationSpringRepository organizationSpringRepository;

}
