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
    User user = new User();
    user.setEmail(entity.getEmail());
    user.setFirstName(entity.getFirstName());
    user.setLastName(entity.getLastName());
    user.setImageUrl(entity.getImageUrl());
    user.setPassword(entity.getPassword());
    user.setSoftDeleted(entity.isSoftDeleted());
    user.setRole(roleEntityMapper.toDomain(entity.getRole()));
    return user;
  }

  public UserEntity toEntity(User user) {
    if (user == null) {
      return null;
    }
    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(user.getEmail());
    userEntity.setFirstName(user.getFirstName());
    userEntity.setLastName(user.getLastName());
    userEntity.setPassword(user.getPassword());
    userEntity.setImageUrl(user.getImageUrl());
    userEntity.setRole(roleEntityMapper.toEntity(user.getRole()));
    return userEntity;
  }
}
