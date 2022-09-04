package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.domain.Organization;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetOrganizationUseCaseService implements IGetOrganizationUseCase {

  private final IOrganizationRepository organizationRepository;
  private final ISlideRepository slideRepository;

  public Organization get() {
    Organization organization = organizationRepository.getOrganization();
    organization.setSlides(slideRepository.findAllSortedByPosition());
    return organization;
  }
}

