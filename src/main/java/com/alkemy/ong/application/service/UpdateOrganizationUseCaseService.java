package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.service.usecase.IUpdateOrganizationUseCase;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.SocialMedia;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateOrganizationUseCaseService implements IUpdateOrganizationUseCase {

  private final IOrganizationRepository organizationRepository;

  @Transactional
  @Override
  public Organization update(Organization organization) {
    Organization savedOrganization = organizationRepository.getOrganization();
    changeNotNullFields(savedOrganization, organization);
    return organizationRepository.update(savedOrganization);
  }

  private void changeNotNullFields(Organization savedOrganization, Organization organization) {
    if (organization.getName() != null) {
      savedOrganization.setName(organization.getName());
    }
    if (organization.getImage() != null) {
      savedOrganization.setImage(organization.getImage());
    }
    if (organization.getPhone() != null) {
      savedOrganization.setPhone(organization.getPhone());
    }
    if (organization.getAddress() != null) {
      savedOrganization.setAddress(organization.getAddress());
    }
    if (organization.getEmail() != null) {
      savedOrganization.setEmail(organization.getEmail());
    }
    if (organization.getSocialMedia() != null) {
      changeSocialMediaFieldsNotNull(savedOrganization.getSocialMedia(),
          organization.getSocialMedia());
    }
    if (organization.getAboutUsText() != null) {
      savedOrganization.setAboutUsText(organization.getAboutUsText());
    }
    if (organization.getWelcomeText() != null) {
      savedOrganization.setWelcomeText(organization.getWelcomeText());
    }
  }

  private void changeSocialMediaFieldsNotNull(SocialMedia savedSocialMedia,
      SocialMedia socialMedia) {
    if (socialMedia.getFacebookUrl() != null) {
      savedSocialMedia.setFacebookUrl(socialMedia.getFacebookUrl());
    }
    if (socialMedia.getLinkedInUrl() != null) {
      savedSocialMedia.setLinkedInUrl(socialMedia.getLinkedInUrl());
    }
    if (socialMedia.getInstagramUrl() != null) {
      savedSocialMedia.setInstagramUrl(socialMedia.getInstagramUrl());
    }
  }

}