package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMemberSpringRepository extends JpaRepository<MemberEntity, Long> {

}
