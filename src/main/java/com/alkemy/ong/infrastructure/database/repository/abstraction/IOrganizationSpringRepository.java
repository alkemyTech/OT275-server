package com.alkemy.ong.infrastructure.database.repository.abstraction;

import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrganizationSpringRepository extends JpaRepository<OrganizationEntity, Long> {

}
