package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.rest.request.OrganizationUpdateRequest;
import com.alkemy.ong.infrastructure.rest.response.OrganizationUpdateResponse;
import org.springframework.stereotype.Component;

@Component
public class OrganizationUpdateMapper {

  public Organization toDomain(OrganizationUpdateRequest request) {
    if (request == null) {
      return null;
    }
    Organization organization = new Organization();
    organization.setName(request.getName());
    organization.setImage(request.getImageUrl());
    organization.setAddress(request.getAddress());
    organization.setPhone(request.getPhone());
    organization.setEmail(request.getEmail());
    organization.setFacebookUrl(request.getFacebookUrl());
    organization.setLinkedInUrl(request.getLinkedInUrl());
    organization.setInstagramUrl(request.getInstagramUrl());
    organization.setAboutUsText(request.getAboutUsText());
    organization.setWelcomeText(request.getWelcomeText());

    return organization;
  }

  public OrganizationUpdateResponse toResponse(Organization organization) {
    if (organization == null) {
      return null;
    }
    OrganizationUpdateResponse organizationUpdateResponse = new OrganizationUpdateResponse();
    organizationUpdateResponse.setName(organization.getName());
    organizationUpdateResponse.setImageUrl(organization.getImage());
    organizationUpdateResponse.setPhone(organization.getPhone());
    organizationUpdateResponse.setAddress(organization.getAddress());
    organizationUpdateResponse.setEmail(organization.getEmail());
    organizationUpdateResponse.setFacebookUrl(organization.getFacebookUrl());
    organizationUpdateResponse.setLinkedInUrl(organization.getLinkedInUrl());
    organizationUpdateResponse.setInstagramUrl(organization.getInstagramUrl());
    organizationUpdateResponse.setAboutUsText(organization.getAboutUsText());
    organizationUpdateResponse.setWelcomeText(organization.getWelcomeText());

    return organizationUpdateResponse;
  }

}
