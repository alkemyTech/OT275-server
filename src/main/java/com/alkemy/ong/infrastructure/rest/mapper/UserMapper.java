package com.alkemy.ong.infrastructure.rest.mapper;


import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.response.ListUserResponse;
import com.alkemy.ong.infrastructure.rest.response.UserResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserResponse toResponse(User user) {
    if (user == null) {
      return null;
    }
    UserResponse userResponse = new UserResponse();
    userResponse.setFirstName(user.getFirstName());
    userResponse.setLastName(user.getLastName());
    userResponse.setEmail(user.getEmail());
    userResponse.setRole(user.getRole().getName());
    return userResponse;
  }


  public ListUserResponse toResponse(List<User> userList) {
    if (userList == null || userList.isEmpty()) {
      return new ListUserResponse(Collections.emptyList());
    }
    List<UserResponse> userResponses = new ArrayList<>(userList.size());
    for (User user : userList) {
      userResponses.add(toResponse(user));
    }
    return new ListUserResponse(userResponses);

  }

}
