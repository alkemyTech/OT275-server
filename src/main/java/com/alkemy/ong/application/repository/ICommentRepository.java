package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.Identifiable;
import java.util.Optional;

public interface ICommentRepository {

  void delete(Identifiable<Long> identifiable);

  boolean exists(Identifiable<Long> identifiable);

  Optional<Comment> find(Identifiable<Long> identifiable);

}