package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.rest.response.PublicOrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublicOrganizationMapper {
  public PublicOrganizationResponse toResponse(Organization organization){
    PublicOrganizationResponse publicOrganizationResponse=new PublicOrganizationResponse();
    publicOrganizationResponse.setName(organization.getName());
    publicOrganizationResponse.setImageUrl(organization.getImageUrl());
    publicOrganizationResponse.setPhone(organization.getPhone());
    publicOrganizationResponse.setPhone(organization.getPhone());
    publicOrganizationResponse.setAdress(organization.getAdress());
    return publicOrganizationResponse;

  }

}
