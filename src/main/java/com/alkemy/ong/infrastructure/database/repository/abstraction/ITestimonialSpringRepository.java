package com.alkemy.ong.infrastructure.database.repository.abstraction;

import com.alkemy.ong.infrastructure.database.entity.TestimonialEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestimonialSpringRepository extends JpaRepository<TestimonialEntity, Long> {

  @Modifying
  @Query("UPDATE TestimonialEntity t SET t.softDeleted = true WHERE t.testimonialId = :id")
  void softDelete(@Param("id") Long id);

  @Query(value = "SELECT t FROM TestimonialEntity t "
      + "WHERE t.softDeleted = false AND t.testimonialId = :id")
  Optional<TestimonialEntity> exists(@Param("id") Long id);

}
