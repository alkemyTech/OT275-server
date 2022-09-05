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

  public User toDomain(UserEntity entity) {
    User user = new User();
    user.setId(entity.getUserId());
    user.setEmail(entity.getEmail());
    user.setFirstName(entity.getFirstName());
    user.setLastName(entity.getLastName());
    user.setImageUrl(entity.getImageUrl());
    user.setPassword(entity.getPassword());
    user.setRole(roleEntityMapper.toDomain(entity.getRole()));
    user.setToken(JwtUtils.create(entity));
    return user;
  }

  public List<User> toDomain(List<UserEntity> userEntityList) {
    if (userEntityList == null || userEntityList.isEmpty()) {
      return Collections.emptyList();
    }
    List<User> userList = new ArrayList<>(userEntityList.size());
    for (UserEntity userEntity : userEntityList) {
      userList.add(toDomain(userEntity));
    }
    return userList;
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
