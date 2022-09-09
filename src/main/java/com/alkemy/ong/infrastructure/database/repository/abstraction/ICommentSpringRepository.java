package com.alkemy.ong.infrastructure.database.repository.abstraction;

import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentSpringRepository extends JpaRepository<CommentEntity, Long> {

  List<CommentEntity> findAllByOrderByCreateTimestampAsc();
}