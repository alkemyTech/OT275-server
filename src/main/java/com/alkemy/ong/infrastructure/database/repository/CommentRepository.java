package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.database.repository.abstraction.ICommentSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentRepository implements ICommentRepository {

  private final ICommentSpringRepository commentSpringRepository;

  @Override
  public void delete(Identifiable<Long> identifiable) {
    commentSpringRepository.deleteById(identifiable.getId());
  }

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return commentSpringRepository.existsById(identifiable.getId());
  }
}