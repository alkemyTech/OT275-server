package com.alkemy.ong.infrastructure.rest.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListUserResponse {

  private List<UserResponse> users;

}
