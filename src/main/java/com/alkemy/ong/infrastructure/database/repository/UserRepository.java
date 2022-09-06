package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IUserRepository;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.mapper.UserEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.IUserSpringRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

  private final IUserSpringRepository userSpringRepository;
  private final UserEntityMapper userEntityMapper;

  @Override
  public void delete(Identifiable<Long> identifiable) {
    userSpringRepository.softDelete(identifiable.getId());
  }

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return userSpringRepository.exists(identifiable.getId()).isPresent();
  }

  @Override
  public User findBy(Identifiable<Long> identifiable) {
    return userEntityMapper.toDomain(
        userSpringRepository.findByUserIdAndSoftDeletedFalse(identifiable.getId()));
  }

  @Override
  public Optional<User> findBy(String email) {
    Optional<UserEntity> userEntity = userSpringRepository.findByEmail(email);
    if (userEntity.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(userEntityMapper.toDomain(userEntity.get()));
  }

  @Override
  public User add(User user) {
    UserEntity userEntity = userEntityMapper.toEntity(user);
    userEntity.setSoftDeleted(false);
    return userEntityMapper.toDomain(userSpringRepository.save(userEntity));
  }

  @Override
  public List<User> findAll() {
    return userEntityMapper.toDomain(userSpringRepository.findAllBySoftDeletedFalse());
  }

  @Override
  public User update(User user) {
    UserEntity updatedUserEntity = userEntityMapper.toEntity(user);
    updatedUserEntity.setSoftDeleted(false);
    return userEntityMapper.toDomain(userSpringRepository.save(updatedUserEntity));
  }

}