package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.UserRegisterRequest;
import com.alkemy.ong.infrastructure.rest.response.PublicOrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublicOrganizationMapper {

    public PublicOrganizationResponse toResponse (Organization organization){
      if (organization == null) {
        return null;
      }
    PublicOrganizationResponse publicOrganizationResponse = new PublicOrganizationResponse();
      publicOrganizationResponse.setName(organization.getName());
      publicOrganizationResponse.setImage(organization.getImage());
      publicOrganizationResponse.setPhone(organization.getPhone());
      publicOrganizationResponse.setAddress(organization.getAddress());
      return publicOrganizationResponse;
    }
  }
