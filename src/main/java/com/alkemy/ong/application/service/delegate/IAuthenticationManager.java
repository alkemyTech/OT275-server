package com.alkemy.ong.application.service.delegate;

import com.alkemy.ong.domain.User;

public interface IAuthenticationManager {

  void authenticate(User user);

}
