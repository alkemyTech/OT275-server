package com.alkemy.ong.infrastructure.database.repository.abstraction;

import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategorySpringRepository extends JpaRepository<CategoryEntity, Long> {

  @Modifying
  @Query("UPDATE UserEntity C SET C.softDeleted = true WHERE C.userId = :id")
  void softDelete(@Param("id") Long id);

  @Query(value = "SELECT C FROM UserEntity C WHERE C.softDeleted = false AND C.userId = :id")
  Optional<UserEntity> exists(@Param("id") Long id);
}
