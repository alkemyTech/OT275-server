package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.database.entity.RoleEntity;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.mapper.UserEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IRoleSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IUserSpringRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

  private final IUserSpringRepository userSpringRepository;
  private final UserEntityMapper userEntityMapper;
  private final IRoleSpringRepository roleSpringRepository;

  @Override
  public void delete(Identifiable<Long> identifiable) {
    userSpringRepository.softDelete(identifiable.getId());
  }

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return userSpringRepository.exists(identifiable.getId()).isPresent();
  }

  @Override
  public Optional<User> find(String email) {
    return userSpringRepository.findByEmail(email).map(userEntityMapper::toDomain);
  }

  @Override
  public User add(User user) {
    UserEntity userEntity = userEntityMapper.toEntity(user);
    String roleName = user.getRole().getName();
    userEntity.setRole(getRoleEntity(roleName));
    userEntity.setSoftDeleted(false);
    return userEntityMapper.toDomain(userSpringRepository.save(userEntity));
  }

  private RoleEntity getRoleEntity(String roleName) {
    return roleSpringRepository.findByName(roleName);
  }
}