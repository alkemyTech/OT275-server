package com.alkemy.ong.infrastructure.database.repository.abstraction;

import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategorySpringRepository extends JpaRepository<CategoryEntity, Long> {

  @Modifying
  @Query("UPDATE CategoryEntity c SET c.softDeleted = true WHERE c.categoryId = :id")
  void softDelete(@Param("id") Long id);

  @Query(value = "SELECT c FROM CategoryEntity c "
      + "WHERE c.softDeleted = false AND c.categoryId = :id")
  Optional<CategoryEntity> exists(@Param("id") Long id);

  CategoryEntity findByCategoryIdAndSoftDeletedFalse(Long id);

}
