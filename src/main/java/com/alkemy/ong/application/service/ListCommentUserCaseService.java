package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.application.service.usecase.IListCommentUseCase;
import com.alkemy.ong.domain.Comment;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListCommentUserCaseService implements IListCommentUseCase {

  ICommentRepository commentRepository;

  @Override
  public List<Comment> findAllOrdered() {
    return commentRepository.findAllOrdered();
  }
}