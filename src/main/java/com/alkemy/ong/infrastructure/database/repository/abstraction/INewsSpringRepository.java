package com.alkemy.ong.infrastructure.database.repository.abstraction;

import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface INewsSpringRepository extends JpaRepository<NewsEntity, Long> {

  @Modifying
  @Query("Update NewsEntity n SET n.softDeleted = true WHERE n.newsId = :id")
  void softDelete(@Param("id") Long id);

  @Query("SELECT n FROM NewsEntity n WHERE n.softDeleted = false AND n.newsId = :id")
  Optional<NewsEntity> exists(@Param("id") Long id);

}
