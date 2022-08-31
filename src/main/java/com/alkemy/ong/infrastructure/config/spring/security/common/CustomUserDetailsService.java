package com.alkemy.ong.infrastructure.config.spring.security.common;

import com.alkemy.ong.application.exception.ErrorMessage;
import com.alkemy.ong.application.exception.InvalidCredentials;
import com.alkemy.ong.application.exception.ObjectNotFound;
import com.alkemy.ong.application.service.delegate.IAuthenticateUser;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IUserSpringRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService, IAuthenticateUser {

  private final IUserSpringRepository userSpringRepository;
  private final AuthenticationManager authenticationManager;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> userEntity = userSpringRepository.findByEmail(username);
    if (userEntity.isEmpty()) {
      throw new ObjectNotFound(ErrorMessage.OBJECT_NOT_FOUND.getMessage("User"));
    }
    return userEntity.get();
  }

  @Override
  public void authenticate(User user) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          user.getEmail(),
          user.getPassword()));
    } catch (Exception e) {
      throw new InvalidCredentials(ErrorMessage.INVALID_CREDENTIALS.getMessage());
    }
  }
}
