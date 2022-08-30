package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetOrganizationUseCaseService implements IGetOrganizationUseCase {

  private final IOrganizationRepository organizationRepository;

  @Transactional
  @Override
  public void getAll(IdentifiableTwo<Long> identifiableTwo) {

    organizationRepository.getAll(identifiableTwo);
  }

}

