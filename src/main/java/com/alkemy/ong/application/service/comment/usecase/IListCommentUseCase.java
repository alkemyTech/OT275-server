package com.alkemy.ong.application.service.comment.usecase;

import com.alkemy.ong.domain.Comment;
import java.util.List;

public interface IListCommentUseCase {

  List<Comment> findAllOrderedByTimestamp();
}
