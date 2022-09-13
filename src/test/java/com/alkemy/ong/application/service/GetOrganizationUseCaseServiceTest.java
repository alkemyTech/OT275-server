package com.alkemy.ong.application.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.application.service.organization.GetOrganizationUseCaseService;
import com.alkemy.ong.domain.Organization;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetOrganizationUseCaseServiceTest {

  private GetOrganizationUseCaseService getOrganizationUseCaseService;

  @Mock
  private IOrganizationRepository organizationRepository;

  @Mock
  private ISlideRepository slideRepository;

  @Mock
  private Organization organization;

  @BeforeEach
  void setUp() {
    getOrganizationUseCaseService = new GetOrganizationUseCaseService(organizationRepository,
        slideRepository);
  }

  @Test
  void shouldGetOrganization() {
    given(organizationRepository.getOrganization()).willReturn(organization);
    given(slideRepository.findAllSortedByPosition()).willReturn(Collections.emptyList());

    getOrganizationUseCaseService.get();

    verify(organizationRepository).getOrganization();
    verify(slideRepository).findAllSortedByPosition();
  }
}
