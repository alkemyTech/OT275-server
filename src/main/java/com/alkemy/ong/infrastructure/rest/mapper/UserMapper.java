package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.rest.response.UserResponse;

public class UserMapper implements IUserMapper{

  @Override
  public User toDomain(UserEntity entity) {
    User user = new User();

    user.setEmail(entity.getEmail());

    return user;
  }

  @Override
  public UserResponse fromDomain(User user) {
    UserResponse userResponse = new UserResponse();

    userResponse.setEmail(user.getEmail());

    return userResponse;
  }


}
