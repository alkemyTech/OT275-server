package com.alkemy.ong.infrastructure.database.repository.abstraction;

import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberSpringRepository extends JpaRepository<MemberEntity, Long> {

  @Modifying
  @Query("UPDATE MemberEntity u SET u.softDeleted = true WHERE u.memberId = :id")
  void softDelete(@Param("id") Long id);

  @Query(value = "SELECT u FROM MemberEntity u WHERE u.softDeleted = false AND u.memberId = :id")
  Optional<MemberEntity> exists(@Param("id") Long id);

  Page<MemberEntity> findAllBySoftDeletedFalse(Pageable pageable);
}
