package com.alkemy.ong.infrastructure.rest.mapper.organization;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.rest.mapper.common.SocialMediaMapper;
import com.alkemy.ong.infrastructure.rest.request.UpdateOrganizationRequest;
import com.alkemy.ong.infrastructure.rest.response.UpdateOrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateOrganizationMapper {

  private final SocialMediaMapper socialMediaMapper;

  public Organization toDomain(UpdateOrganizationRequest updateOrganizationRequest) {
    if (updateOrganizationRequest == null) {
      return null;
    }
    Organization organization = new Organization();
    organization.setName(updateOrganizationRequest.getName());
    organization.setImage(updateOrganizationRequest.getImageUrl());
    organization.setAddress(updateOrganizationRequest.getAddress());
    organization.setPhone(updateOrganizationRequest.getPhone());
    organization.setEmail(updateOrganizationRequest.getEmail());
    organization.setSocialMedia(
        socialMediaMapper.toDomain(updateOrganizationRequest.getSocialMedia()));
    organization.setAboutUsText(updateOrganizationRequest.getAboutUsText());
    organization.setWelcomeText(updateOrganizationRequest.getWelcomeText());

    return organization;
  }

  public UpdateOrganizationResponse toResponse(Organization organization) {
    if (organization == null) {
      return null;
    }
    UpdateOrganizationResponse updateOrganizationResponse = new UpdateOrganizationResponse();
    updateOrganizationResponse.setName(organization.getName());
    updateOrganizationResponse.setImageUrl(organization.getImage());
    updateOrganizationResponse.setPhone(organization.getPhone());
    updateOrganizationResponse.setAddress(organization.getAddress());
    updateOrganizationResponse.setEmail(organization.getEmail());
    updateOrganizationResponse.setSocialMedia(
        socialMediaMapper.toResponse(organization.getSocialMedia()));
    updateOrganizationResponse.setAboutUsText(organization.getAboutUsText());
    updateOrganizationResponse.setWelcomeText(organization.getWelcomeText());

    return updateOrganizationResponse;
  }
}