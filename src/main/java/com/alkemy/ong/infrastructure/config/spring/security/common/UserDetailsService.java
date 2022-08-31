package com.alkemy.ong.infrastructure.config.spring.security.common;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IUserSpringRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserDetailsService implements
    org.springframework.security.core.userdetails.UserDetailsService {

  private IUserSpringRepository userSpringRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = userSpringRepository.getByEmail(username);
    if (userEntity == null) {
      throw new ObjectNotFound(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    return userEntity;
  }
}
