package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.rest.response.GetOrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetOrganizationMapper {

  public GetOrganizationResponse toResponse(Organization organization) {
    if (organization == null) {
      return null;
    }
    GetOrganizationResponse getOrganizationResponse = new GetOrganizationResponse();
    getOrganizationResponse.setName(organization.getName());
    getOrganizationResponse.setImage(organization.getImage());
    getOrganizationResponse.setPhone(organization.getPhone());
    getOrganizationResponse.setAddress(organization.getAddress());
    return getOrganizationResponse;
  }
}