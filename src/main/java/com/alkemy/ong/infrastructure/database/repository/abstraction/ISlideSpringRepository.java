package com.alkemy.ong.infrastructure.database.repository.abstraction;

import com.alkemy.ong.infrastructure.database.entity.SlideEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISlideSpringRepository extends JpaRepository<SlideEntity, Long> {

  List<SlideEntity> findAll();

  List<SlideEntity> findAllByOrderByPosition();

  @Query("SELECT COALESCE(MAX(s.position), 0) FROM SlideEntity s")
  Integer maxPosition();

}
