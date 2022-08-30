package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IOrganizationRepository;

import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.database.repository.OrganizationRepository;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetOrganizationUseCaseService implements IGetOrganizationUseCase {

  private final IOrganizationRepository organizationRepository;

  public Organization getPublicOrganization() {

    return organizationRepository.getPublicOrganization();
  }
}

