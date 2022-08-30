package com.alkemy.ong.infrastructure.database.repository.abstraction;

import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import java.util.Optional;
import java.util.stream.DoubleStream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserSpringRepository extends JpaRepository<UserEntity, Long> {

  @Modifying
  @Query("UPDATE UserEntity u SET u.softDeleted = true WHERE u.userId = :id")
  void softDelete(@Param("id") Long id);

  @Query(value = "SELECT u FROM UserEntity u WHERE u.softDeleted = false AND u.userId = :id")
  Optional<UserEntity> exists(@Param("id") Long id);

  Optional<UserEntity> findByEmail(String email);
}