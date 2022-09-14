package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.Identifiable;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import com.alkemy.ong.infrastructure.database.mapper.CommentEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.abstraction.ICommentSpringRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentRepository implements ICommentRepository {

  private final ICommentSpringRepository commentSpringRepository;

  private final CommentEntityMapper commentMapper;

  @Override
  public void delete(Identifiable<Long> identifiable) {
    commentSpringRepository.deleteById(identifiable.getId());
  }

  @Override
  public boolean exists(Identifiable<Long> identifiable) {
    return commentSpringRepository.existsById(identifiable.getId());
  }

  @Override
  public Optional<Comment> find(Identifiable<Long> identifiable) {
    return commentSpringRepository.findById(identifiable.getId()).map(commentMapper::toDomain);
  }

  @Override
  public List<Comment> findAllOrderedByTimestamp() {
    return commentMapper.toDomain(commentSpringRepository.findAllByOrderByCreateTimestampAsc());
  }

  @Override
  public Comment create(Comment comment) {
    CommentEntity commentEntity = commentMapper.toEntity(comment);
    return commentMapper.toDomain(commentSpringRepository.save(commentEntity));
  }

  @Override
  public Comment update(Comment comment) {
    CommentEntity commentEntity = commentMapper.toEntity(comment);
    return commentMapper.toDomain(commentSpringRepository.save(commentEntity));
  }
}