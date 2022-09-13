package com.alkemy.ong.infrastructure.rest.mapper.user;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.response.ListUserResponse;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListUserMapper {

  private final GetUserMapper getUserMapper;

  public ListUserResponse toResponse(List<User> users) {
    if (users == null || users.isEmpty()) {
      return new ListUserResponse(Collections.emptyList());
    }
    return new ListUserResponse(getUserMapper.toResponse(users));
  }

}
