package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentSpringRepository extends JpaRepository<CommentEntity, Long> {

}
