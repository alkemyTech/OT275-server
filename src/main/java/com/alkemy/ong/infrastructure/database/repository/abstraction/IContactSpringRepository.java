package com.alkemy.ong.infrastructure.database.repository.abstraction;

import com.alkemy.ong.infrastructure.database.entity.ContactEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactSpringRepository extends JpaRepository<ContactEntity, Long> {

  List<ContactEntity> findAllByDeletedAtNull();
}
