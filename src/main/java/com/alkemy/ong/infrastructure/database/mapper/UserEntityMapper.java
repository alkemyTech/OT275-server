package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.config.spring.security.common.JwtUtils;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityMapper {

  private final RoleEntityMapper roleEntityMapper;

  public User toDomain(UserEntity userEntity) {
    if (userEntity == null) {
      return null;
    }
    User user = new User();
    user.setId(userEntity.getUserId());
    user.setEmail(userEntity.getEmail());
    user.setFirstName(userEntity.getFirstName());
    user.setLastName(userEntity.getLastName());
    user.setImageUrl(userEntity.getImageUrl());
    user.setPassword(userEntity.getPassword());
    user.setRole(roleEntityMapper.toDomain(userEntity.getRole()));
    user.setToken(JwtUtils.create(userEntity));
    return user;
  }

  public List<User> toDomain(List<UserEntity> userEntities) {
    if (userEntities == null || userEntities.isEmpty()) {
      return Collections.emptyList();
    }
    List<User> users = new ArrayList<>(userEntities.size());
    for (UserEntity userEntity : userEntities) {
      users.add(toDomain(userEntity));
    }
    return users;
  }

  public UserEntity toEntity(User user) {
    if (user == null) {
      return null;
    }
    UserEntity userEntity = new UserEntity();
    userEntity.setUserId(user.getId());
    userEntity.setEmail(user.getEmail());
    userEntity.setFirstName(user.getFirstName());
    userEntity.setLastName(user.getLastName());
    userEntity.setPassword(user.getPassword());
    userEntity.setImageUrl(user.getImageUrl());
    userEntity.setRole(roleEntityMapper.toEntity(user.getRole()));
    return userEntity;
  }

}
