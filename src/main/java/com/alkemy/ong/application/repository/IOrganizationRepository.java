package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Organization;

public interface IOrganizationRepository {

  Organization getOrganization();

  Organization update(Organization organization);

}

