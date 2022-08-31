package com.alkemy.ong.infrastructure.config.spring.security.common;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IUserSpringRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

  private IUserSpringRepository userSpringRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> userEntity = userSpringRepository.findByEmail(username);
    if (userEntity.isEmpty()) {
      throw new ObjectNotFound(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    return userEntity.get();
  }
}
