package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

  public User toDomain(UserEntity entity) {
    User user = new User();

    user.setEmail(entity.getEmail());

    return user;
  }

}
