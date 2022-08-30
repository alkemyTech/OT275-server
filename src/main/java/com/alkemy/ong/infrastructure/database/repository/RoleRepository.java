package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IRoleRepository;
import com.alkemy.ong.domain.Role;
import com.alkemy.ong.infrastructure.database.mapper.RoleEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IRoleSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RoleRepository implements IRoleRepository {

  private final IRoleSpringRepository roleSpringRepository;
  private final RoleEntityMapper roleEntityMapper;

  @Override
  public Role findRoleUser() {
    return roleEntityMapper.toDomain(roleSpringRepository.findByName(
        com.alkemy.ong.infrastructure.config.spring.security.common.Role.USER.getFullRoleName()));
  }

}
