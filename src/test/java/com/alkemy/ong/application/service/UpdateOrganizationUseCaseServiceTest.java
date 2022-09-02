package com.alkemy.ong.application.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.domain.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateOrganizationUseCaseServiceTest {

  private UpdateOrganizationUseCaseService updateOrganizationUseCaseService;

  @Mock
  private IOrganizationRepository organizationRepository;

  @Mock
  private Organization organization;

  @BeforeEach
  void setUp() {
    updateOrganizationUseCaseService = new UpdateOrganizationUseCaseService(organizationRepository);
  }

  @Test
  void shouldUpdateOrganization() {
    given(organizationRepository.getOrganization()).willReturn(organization);
    given(organizationRepository.update(organization)).willReturn(organization);

    updateOrganizationUseCaseService.update(organization);

    verify(organizationRepository).update(organization);
  }

}
