package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Role;
import com.alkemy.ong.infrastructure.database.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleEntityMapper {

  public Role toDomain(RoleEntity roleEntity) {
    if (roleEntity == null) {
      return null;
    }

    Role role = new Role();
    role.setId(roleEntity.getRoleId());
    role.setName(roleEntity.getName());
    role.setDescription(roleEntity.getDescription());

    return role;
  }

  public RoleEntity toEntity(Role role) {
    if (role == null) {
      return null;
    }
    RoleEntity roleEntity = new RoleEntity();
    roleEntity.setRoleId(role.getId());
    roleEntity.setName(role.getName());
    roleEntity.setDescription(role.getDescription());
    return roleEntity;
  }
}
