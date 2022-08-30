package com.alkemy.ong.infrastructure.database.repository.abstraction;

import com.alkemy.ong.infrastructure.database.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleSpringRepository extends JpaRepository<RoleEntity, Long> {

  RoleEntity findByName(String roleName);
}
