package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationMapper {

  public Organization toDomain(OrganizationEntity entity) {
    if (entity == null) {
      return null;
    }

    Organization organization = new Organization();
    organization.setName(entity.getName());
    organization.setImage(entity.getImageUrl());
    organization.setPhone(entity.getPhone());
    organization.setAddress(entity.getAddress());
    organization.setEmail(entity.getEmail());
    organization.setFacebookUrl(entity.getFacebookUrl());
    organization.setLinkedInUrl(entity.getLinkedInUrl());
    organization.setInstagramUrl(entity.getInstagramUrl());
    organization.setAboutUsText(entity.getAboutUsText());
    organization.setWelcomeText(entity.getWelcomeText());
    return organization;
  }

  public void update(OrganizationEntity entity, Organization organization) {
    if (organization.getName() != null) {
      entity.setName(organization.getName());
    }
    if (organization.getImage() != null) {
      entity.setImageUrl(organization.getImage());
    }
    if (organization.getPhone() != null) {
      entity.setPhone(organization.getPhone());
    }
    if (organization.getAddress() != null) {
      entity.setAddress(organization.getAddress());
    }
    if (organization.getEmail() != null) {
      entity.setEmail(organization.getEmail());
    }
    if (organization.getFacebookUrl() != null) {
      entity.setFacebookUrl(organization.getFacebookUrl());
    }
    if (organization.getLinkedInUrl() != null) {
      entity.setLinkedInUrl(organization.getLinkedInUrl());
    }
    if (organization.getInstagramUrl() != null) {
      entity.setLinkedInUrl(organization.getLinkedInUrl());
    }
    if (organization.getAboutUsText() != null) {
      entity.setAboutUsText(organization.getAboutUsText());
    }
    if (organization.getWelcomeText() != null) {
      entity.setWelcomeText(organization.getWelcomeText());
    }

  }
}
