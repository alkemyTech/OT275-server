package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationMapper {


  public Organization toDomain(OrganizationEntity entity) {

    Organization organization = new Organization();
    organization.setName(entity.getName());
    organization.setImageUrl(entity.getImageUrl());
    organization.setPhone(entity.getPhone());
    organization.setAddress(entity.getAddress());
    return organization;

  }
}
