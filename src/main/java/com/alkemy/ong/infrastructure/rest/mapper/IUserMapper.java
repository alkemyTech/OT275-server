package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.rest.response.UserResponse;

public interface IUserMapper {

  User toDomain(UserEntity entity);
  UserResponse fromDomain(User user);

}
