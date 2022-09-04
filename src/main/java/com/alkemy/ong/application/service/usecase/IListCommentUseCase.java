package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Comment;
import java.util.List;

public interface IListCommentUseCase {
  List<Comment> findAllOrdered();
}
