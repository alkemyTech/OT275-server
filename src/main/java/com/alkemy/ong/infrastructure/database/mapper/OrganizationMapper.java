package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.SocialMedia;
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
    organization.setSocialMedia(buildSocialMedia(entity));
    organization.setAboutUsText(entity.getAboutUsText());
    organization.setWelcomeText(entity.getWelcomeText());

    return organization;
  }

  public OrganizationEntity toEntity(Organization organization) {
    if (organization == null) {
      return null;
    }

    OrganizationEntity entity = new OrganizationEntity();
    entity.setName(organization.getName());
    entity.setImageUrl(organization.getImage());
    entity.setPhone(organization.getPhone());
    entity.setAddress(organization.getAddress());
    entity.setEmail(organization.getEmail());
    entity.setFacebookUrl(organization.getSocialMedia().getFacebookUrl());
    entity.setLinkedInUrl(organization.getSocialMedia().getLinkedInUrl());
    entity.setInstagramUrl(organization.getSocialMedia().getInstagramUrl());
    entity.setAboutUsText(organization.getAboutUsText());
    entity.setWelcomeText(organization.getWelcomeText());

    return entity;
  }

  private SocialMedia buildSocialMedia(OrganizationEntity entity) {
    SocialMedia socialMedia = new SocialMedia();
    socialMedia.setInstagramUrl(entity.getInstagramUrl());
    socialMedia.setLinkedInUrl(entity.getLinkedInUrl());
    socialMedia.setFacebookUrl(entity.getFacebookUrl());

    return socialMedia;
  }

}
