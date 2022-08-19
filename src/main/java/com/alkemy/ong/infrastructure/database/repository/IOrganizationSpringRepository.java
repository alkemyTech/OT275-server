package com.alkemy.ong.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alkemy.ong.infrastructure.database.entity.OrganitzationEntity;

public interface IOrganizationSpringRepository extends JpaRepository<OrganitzationEntity, Long> {

}
