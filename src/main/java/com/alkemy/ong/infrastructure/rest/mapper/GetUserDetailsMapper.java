package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.response.GetUserDetailsResponse;
import org.springframework.stereotype.Component;

@Component
public class GetUserDetailsMapper {

  public GetUserDetailsResponse toResponse(User user) {
    if (user == null) {
      return null;
    }
    GetUserDetailsResponse getUserDetailsResponse = new GetUserDetailsResponse();
    getUserDetailsResponse.setId(user.getId());
    getUserDetailsResponse.setEmail(user.getEmail());
    getUserDetailsResponse.setFirstName(user.getFirstName());
    getUserDetailsResponse.setLastName(user.getLastName());
    getUserDetailsResponse.setImageUrl(user.getImageUrl());
    getUserDetailsResponse.setRole(user.getRole().getName());
    return getUserDetailsResponse;
  }
}




