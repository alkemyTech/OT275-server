package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.config.spring.security.common.JwtUtils;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityMapper {

  private final JwtUtils jwtUtils;
  private final RoleEntityMapper roleEntityMapper;

  public User toDomain(UserEntity entity) {
    return User.builder()
        .email(entity.getEmail())
        .firstName(entity.getFirstName())
        .lastName(entity.getLastName())
        .imageUrl(entity.getImageUrl())
        .password(entity.getPassword())
        .softDeleted(entity.isSoftDeleted())
        .role(roleEntityMapper.toDomain(entity.getRole()))
        .build();
  }

}
