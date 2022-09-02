package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.service.usecase.IUpdateOrganizationUseCase;
import com.alkemy.ong.domain.Organization;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateOrganizationUseCaseService implements IUpdateOrganizationUseCase {

  private final IOrganizationRepository organizationRepository;

  @Transactional
  @Override
  public Organization update(Organization organization) {
    return organizationRepository.update(organization);
  }

}