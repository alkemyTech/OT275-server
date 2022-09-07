package com.alkemy.ong.infrastructure.database.repository.abstraction;

import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IActivitySpringRepository extends JpaRepository<ActivityEntity, Long> {

  Optional<ActivityEntity> findByName(String name);

  @Query(value = "SELECT a FROM ActivityEntity a "
      + "WHERE a.softDeleted = false AND a.activityId = :id")
  Optional<ActivityEntity> exists(@Param("id") Long id);

}