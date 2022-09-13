package com.alkemy.ong.infrastructure.rest.mapper.user;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.response.user.GetUserResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class GetUserMapper {

  public GetUserResponse toResponse(User user) {
    if (user == null) {
      return null;
    }
    GetUserResponse getUserResponse = new GetUserResponse();
    getUserResponse.setId(user.getId());
    getUserResponse.setFirstName(user.getFirstName());
    getUserResponse.setLastName(user.getLastName());
    getUserResponse.setEmail(user.getEmail());
    getUserResponse.setRole(user.getRole().getName());
    return getUserResponse;
  }

  public List<GetUserResponse> toResponse(List<User> users) {
    List<GetUserResponse> getUserResponses = new ArrayList<>(users.size());
    for (User user : users) {
      getUserResponses.add(toResponse(user));
    }
    return getUserResponses;
  }

}
