package com.alkemy.ong.infrastructure.rest.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListUserResponse {

  private List<UserResponse> users;

}
