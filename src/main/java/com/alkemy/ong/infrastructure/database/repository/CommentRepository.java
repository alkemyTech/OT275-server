package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentRepository implements ICommentRepository {

  private final ICommentSpringRepository commentSpringRepository;

}
